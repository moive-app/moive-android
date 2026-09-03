package com.moive.app.presentation.condition

import androidx.compose.runtime.Immutable

interface ConditionContract {
    @Immutable
    data class State(
        val step: Step = Step.INPUT,
        val selectedPlace: String? = null,
    )

    enum class Step {
        INPUT,
        SEARCH,
        CONFIRM;
    }
}
