package com.moive.app.core.extensions

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri

fun Context.openUrl(url: String?): Boolean {
    if (url == null) return false
    return runCatching {
        startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
    }.isSuccess
}

fun Context.openKakaoMapRoute(
    startLatitude: Double,
    startLongitude: Double,
    endLatitude: Double,
    endLongitude: Double,
) {
    val query = "sp=$startLatitude,$startLongitude&ep=$endLatitude,$endLongitude&by=publictransit"
    val appUrl = "kakaomap://route?$query"
    val webUrl = "http://m.map.kakao.com/scheme/route?$query"

    if (!openUrl(appUrl)) openUrl(webUrl)
}

fun Context.isNotificationEnabled(): Boolean =
    NotificationManagerCompat.from(this).areNotificationsEnabled()

fun Context.navigateToAppNotificationSettings() {
    val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        }
    } else {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = "package:$packageName".toUri()
        }
    }
    startActivity(intent)
}
