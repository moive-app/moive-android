package com.moive.app.presentation.mypage

import androidx.compose.runtime.Immutable

interface MyPageContract {
    @Immutable
    data class State(
        val isLogoutConfirmVisible: Boolean = false,
    )

    sealed class SideEffect {
        data object NavigateToLogin : SideEffect()
        data class OnShowToast(val msg: String) : SideEffect()
    }
}
