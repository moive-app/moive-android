package com.moive.app.core.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.moive.app.R

object NotificationChannels {
    private const val DEFAULT_CHANNEL_NAME = "일반 알림"

    fun defaultChannelId(context: Context): String =
        context.getString(R.string.default_notification_channel_id)

    fun createDefaultChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            defaultChannelId(context),
            DEFAULT_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            enableVibration(true)
        }
        context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }
}
