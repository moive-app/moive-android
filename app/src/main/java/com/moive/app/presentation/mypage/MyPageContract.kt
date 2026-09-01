package com.moive.app.presentation.mypage

import androidx.compose.runtime.Immutable

interface MyPageContract {

    sealed class SideEffect {
        data object NavigateToLogin : SideEffect()
        data class OnShowToast(val msg: String) : SideEffect()
    }
}
