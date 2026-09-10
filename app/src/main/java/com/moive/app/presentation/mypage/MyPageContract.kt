package com.moive.app.presentation.mypage

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.toast.ToastType

interface MyPageContract {
    @Immutable
    data class State(
        val hasUnReadAlarm: Boolean = false,
        val myInfoUiState: MyPageUiState = MyPageUiState.IDLE,
        val name: String = "",
        val email: String = "",
        val profileImage: String = "",
        val showLogoutBottomSheet: Boolean = false,
    )

    sealed class SideEffect {
        data object NavigateToLogin : SideEffect()
        data class OnShowToast(val msg: String, val type: ToastType) : SideEffect()
    }
}

sealed interface MyPageUiState {
    data object IDLE : MyPageUiState
    data object Loading : MyPageUiState
    data object Success : MyPageUiState
    data class Failure(
        val msg: String,
    ) : MyPageUiState
}
