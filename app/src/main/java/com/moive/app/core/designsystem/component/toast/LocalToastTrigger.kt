package com.moive.app.core.designsystem.component.toast

import androidx.compose.runtime.staticCompositionLocalOf

val LocalToastTrigger = staticCompositionLocalOf<(message: String, type: ToastType) -> Unit> {
    error("No Toast provided")
}
