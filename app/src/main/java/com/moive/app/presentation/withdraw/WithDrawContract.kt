package com.moive.app.presentation.withdraw

import androidx.compose.runtime.Immutable

interface WithDrawContract {
    @Immutable
    data class State(
        val isAgreed: Boolean = false,
    )
}
