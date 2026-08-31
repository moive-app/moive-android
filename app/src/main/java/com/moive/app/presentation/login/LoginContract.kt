package com.moive.app.presentation.login

import androidx.compose.runtime.Immutable

interface LoginContract {
    @Immutable
    data class State(
        val isLoginComplete: Boolean = false,
    )
}
