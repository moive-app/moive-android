package com.moive.app.presentation.mypage

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.toast.ToastType

interface MyPageContract {
    @Immutable
    data class State(
        val isLogoutConfirmVisible: Boolean = false,
    )

    sealed class SideEffect {
        data object NavigateToLogin : SideEffect()
        data class OnShowToast(val msg: String, val type: ToastType) : SideEffect()
    }
}
