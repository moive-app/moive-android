package com.moive.app.presentation.splash

import androidx.compose.runtime.Immutable

interface SplashContract {

    @Immutable
    data class State(
        val isSplashReady: Boolean = false,
    )

    sealed interface SideEffect {
        data class NavigateToHome(val isAutoLoginSuccess: Boolean) : SideEffect
    }

}
