package com.moive.app.presentation.withdraw

import androidx.compose.runtime.Immutable

interface WithDrawContract {
    @Immutable
    data class State(
        val isAgreed: Boolean = false,
        val showConfirmBottomSheet: Boolean = false,
        val withDrawUiState: WithDrawUiState = WithDrawUiState.IDLE,
    )

    sealed class SideEffect {
        data object NavigateToLogin : SideEffect()
    }
}

sealed interface WithDrawUiState {
    data object IDLE : WithDrawUiState
    data object Loading : WithDrawUiState
    data object Success : WithDrawUiState
    data class Failure(
        val msg: String,
    ) : WithDrawUiState
}
