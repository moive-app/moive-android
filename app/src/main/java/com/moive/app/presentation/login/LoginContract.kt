package com.moive.app.presentation.login

import androidx.compose.runtime.Immutable

interface LoginContract {
    @Immutable
    data class State(
        val needRegister: Boolean = false,
        val isServiceAgreed: Boolean = false,
        val isPrivacyAgreed: Boolean = false,
        val isMarketingAgreed: Boolean = false,
        val isSignUpSubmitting: Boolean = false,
    ) {
        val isBtnEnabled: Boolean = isServiceAgreed && isPrivacyAgreed && !isSignUpSubmitting
    }

    sealed class SideEffect {
        data object NavigateToHome : SideEffect()
        data object NavigateToSignUpComplete : SideEffect()
        data class OnShowToast(val msg: String) : SideEffect()
    }
}
