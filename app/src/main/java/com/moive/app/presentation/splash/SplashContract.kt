package com.moive.app.presentation.splash

interface SplashContract {

    sealed interface SideEffect {
        data object NavigateToHome: SideEffect
        data object NavigateToLogin: SideEffect
    }

}
