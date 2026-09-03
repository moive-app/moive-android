package com.moive.app.presentation.condition

import androidx.lifecycle.ViewModel
import com.moive.app.presentation.condition.ConditionContract.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConditionViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(ConditionContract.State())
    val uiState = _uiState.asStateFlow()

    fun onPlaceSearchBoxClick() {
        _uiState.update { it.copy(step = Step.SEARCH) }
    }

    fun onPlaceItemClick(place: String) {
        _uiState.update { it.copy(step = Step.INPUT, selectedPlace = place) }
    }

    fun onNextButtonClick() {
        _uiState.update { it.copy(step = Step.CONFIRM) }
    }

    fun backToInputStep() {
        _uiState.update { it.copy(step = Step.INPUT) }
    }
}
