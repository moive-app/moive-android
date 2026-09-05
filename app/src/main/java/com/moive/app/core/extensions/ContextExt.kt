package com.moive.app.core.extensions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openUrl(url: String?): Boolean {
    if (url == null) return false
    return runCatching {
        startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
    }.isSuccess
}
