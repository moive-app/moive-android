package com.moive.app.presentation.splash

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.toast.ToastType

interface SplashContract {

    @Immutable
    data class State(
        val isSplashReady: Boolean = false,
    )

    sealed interface SideEffect {
        data class NavigateToHome(val isAutoLoginSuccess: Boolean) : SideEffect
        data class OnShowToast(val msg: String, val type: ToastType) : SideEffect
    }

}
