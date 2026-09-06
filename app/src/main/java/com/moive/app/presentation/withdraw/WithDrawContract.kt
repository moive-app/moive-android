package com.moive.app.presentation.withdraw

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.toast.ToastType

interface WithDrawContract {
    @Immutable
    data class State(
        val isAgreed: Boolean = false,
        val showConfirmBottomSheet: Boolean = false,
    )

    sealed class SideEffect {
        data object NavigateToLogin : SideEffect()
    }
}
