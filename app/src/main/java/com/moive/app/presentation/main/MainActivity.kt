package com.moive.app.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.fcm.FirebaseMessagingManager
import com.moive.app.core.fcm.MoiveFirebaseMessagingService
import com.moive.app.core.network.token.AuthManager
import com.moive.app.data.meeting.repository.MeetingRepository
import com.moive.app.data.notification.repository.NotificationRepository
import com.moive.app.presentation.login.navigation.navigateToLogin
import com.moive.app.presentation.meeting.detail.navigation.navigateToMeetingDetail
import com.moive.app.presentation.notification.NotificationPermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authManager: AuthManager

    @Inject
    lateinit var meetingRepository: MeetingRepository

    @Inject
    lateinit var notificationRepository: NotificationRepository

    @Inject
    lateinit var firebaseMessagingManager: FirebaseMessagingManager

    private val pendingInviteCode = mutableStateOf<String?>(null)

    private val pendingMeetingId = mutableStateOf<Long?>(null)

    private val pendingNotificationId = mutableStateOf<Long?>(null)

    private val notificationPermissionManager = NotificationPermissionManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        pendingInviteCode.value = intent.extractInviteCode()
        pendingMeetingId.value = intent.extractMeetingId()
            ?: savedInstanceState?.getLong(KEY_PENDING_MEETING_ID, -1L)?.takeIf { it != -1L }
        pendingNotificationId.value = intent.extractNotificationId()
            ?: savedInstanceState?.getLong(KEY_PENDING_NOTIFICATION_ID, -1L)?.takeIf { it != -1L }
        intent = Intent()
        setContent {
            MoiveTheme {
                val appState = rememberMainAppState()

                LaunchedEffect(Unit) {
                    authManager.authEvent.collect {
                        appState.navController.navigateToLogin()
                    }
                }

                LaunchedEffect(Unit) {
                    appState.isSignedIn.first { it }
                    notificationPermissionManager.askNotificationPermission()
                }

                LaunchedEffect(Unit) {
                    appState.isSignedIn.filter { it }.collect {
                        val fcmToken = firebaseMessagingManager.getFcmToken()
                        val deviceId = firebaseMessagingManager.getInstallationId()

                        if (fcmToken != null && deviceId != null) {
                            notificationRepository.putDeviceToken(fcmToken, deviceId)
                                .onSuccess {
                                    Timber.tag(TAG).d(DEVICE_TOKEN_PUT_SUCCESS_MESSAGE)
                                }
                                .onFailure { error ->
                                    Timber.tag(TAG).e(error)
                                }
                        }
                    }
                }

                LaunchedEffect(pendingInviteCode.value) {
                    val inviteCode = pendingInviteCode.value ?: return@LaunchedEffect

                    appState.isSignedIn.first { it }

                    meetingRepository.postMeetingJoin(inviteCode)
                        .onSuccess { join ->
                            appState.navController.navigateToMeetingDetail(join.meetingId)
                        }
                        .onFailure { error ->
                            Timber.tag(TAG).e(error)
                        }

                    pendingInviteCode.value = null
                }

                LaunchedEffect(pendingMeetingId.value) {
                    val meetingId = pendingMeetingId.value ?: return@LaunchedEffect

                    appState.isSignedIn.first { it }

                    appState.navController.navigateToMeetingDetail(meetingId)
                    pendingMeetingId.value = null
                }

                LaunchedEffect(pendingNotificationId.value) {
                    val notificationId = pendingNotificationId.value ?: return@LaunchedEffect

                    appState.isSignedIn.first { it }

                    notificationRepository.patchNotificationRead(notificationId)
                        .onFailure { error ->
                            Timber.tag(TAG).e(error)
                        }
                    pendingNotificationId.value = null
                }

                MainScreen(
                    appState = appState,
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        pendingInviteCode.value = intent.extractInviteCode()
        pendingMeetingId.value = intent.extractMeetingId()
        pendingNotificationId.value = intent.extractNotificationId()
        setIntent(Intent())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        pendingMeetingId.value?.let { outState.putLong(KEY_PENDING_MEETING_ID, it) }
        pendingNotificationId.value?.let { outState.putLong(KEY_PENDING_NOTIFICATION_ID, it) }
    }

    companion object {
        private const val TAG = "Invite"
        private const val KEY_PENDING_MEETING_ID = "pendingMeetingId"
        private const val KEY_PENDING_NOTIFICATION_ID = "pendingNotificationId"
        private const val DEVICE_TOKEN_PUT_SUCCESS_MESSAGE = "디바이스 토큰 등록 성공"
    }
}

private fun Intent.extractInviteCode(): String? =
    takeIf { it.action == Intent.ACTION_VIEW }?.data?.lastPathSegment

private fun Intent.extractMeetingId(): Long? {
    val meetingId = getLongExtra(MoiveFirebaseMessagingService.MESSAGE_MEETING_ID, -1L)
    if (meetingId != -1L) removeExtra(MoiveFirebaseMessagingService.MESSAGE_MEETING_ID)
    return meetingId.takeIf { it != -1L }
}

private fun Intent.extractNotificationId(): Long? {
    val notificationId = getLongExtra(MoiveFirebaseMessagingService.MESSAGE_NOTIFICATION_ID, -1L)
    if (notificationId != -1L) removeExtra(MoiveFirebaseMessagingService.MESSAGE_NOTIFICATION_ID)
    return notificationId.takeIf { it != -1L }
}
