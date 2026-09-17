package com.moive.app.core.fcm

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moive.app.R
import com.moive.app.data.notification.repository.NotificationRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@AndroidEntryPoint
class MoiveFirebaseMessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var notificationRepository: NotificationRepository

    @Inject
    lateinit var firebaseMessagingManager: FirebaseMessagingManager

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.tag(TAG).d("token: $token")

        serviceScope.launch {
            val deviceId = firebaseMessagingManager.getInstallationId() ?: return@launch

            notificationRepository.putDeviceToken(token, deviceId)
                .onSuccess {
                    Timber.tag(TAG).d(DEVICE_TOKEN_PUT_SUCCESS_MESSAGE)
                }
                .onFailure { error ->
                    Timber.tag(TAG).e(error)
                }
        }
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.data[MESSAGE_TITLE] ?: return
        val body = remoteMessage.data[MESSAGE_BODY]
        val meetingId = remoteMessage.data[MESSAGE_MEETING_ID]?.toLongOrNull()
        val notificationId = remoteMessage.data[MESSAGE_NOTIFICATION_ID]?.toLongOrNull()

        showNotification(title, body, meetingId, notificationId)
    }

    private fun showNotification(title: String, body: String?, meetingId: Long?, notificationId: Long?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                enableVibration(true)
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        val granted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
        if (!granted) return

        val androidNotificationId = notificationIdGenerator.incrementAndGet()

        val contentIntent = packageManager.getLaunchIntentForPackage(packageName)?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            meetingId?.let { putExtra(MESSAGE_MEETING_ID, it) }
            notificationId?.let { putExtra(MESSAGE_NOTIFICATION_ID, it) }
        } ?: return
        val pendingIntent = PendingIntent.getActivity(
            this,
            androidNotificationId,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.img_logo_white_bg)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(this).notify(androidNotificationId, notification)
    }

    companion object {
        private const val TAG = "FCM"
        const val MESSAGE_MEETING_ID = "meetingId"
        const val MESSAGE_NOTIFICATION_ID = "notificationId"
        private const val MESSAGE_TITLE = "title"
        private const val MESSAGE_BODY = "body"
        private const val CHANNEL_ID = "moive_default_channel"
        private const val CHANNEL_NAME = "일반 알림"
        private const val DEVICE_TOKEN_PUT_SUCCESS_MESSAGE = "디바이스 토큰 등록 성공"
        private val notificationIdGenerator = AtomicInteger(0)
    }
}
