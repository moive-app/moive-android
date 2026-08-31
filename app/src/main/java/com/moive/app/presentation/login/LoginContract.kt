package com.moive.app.presentation.login

import androidx.compose.runtime.Immutable

interface LoginContract {
    @Immutable
    data class State(
        val isLoginComplete: Boolean = false,
        val isServiceAgreed: Boolean = false,
        val isPrivacyAgreed: Boolean = false,
        val isMarketingAgreed: Boolean = false,
    ) {
        val isBtnEnabled: Boolean = isServiceAgreed && isPrivacyAgreed
    }

    sealed class SideEffect {
        data object NavigateToMyPage : SideEffect()
    }
}
