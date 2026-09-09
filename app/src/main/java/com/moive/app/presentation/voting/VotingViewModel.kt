package com.moive.app.presentation.voting

import androidx.lifecycle.ViewModel
import com.moive.app.presentation.voting.VotingContract.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.collections.immutable.persistentSetOf
import javax.inject.Inject

@HiltViewModel
class VotingViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(VotingContract.State())
    val uiState = _uiState.asStateFlow()

    fun onRegionPinClick(regionName: String) {
        _uiState.update {
            it.copy(
                isPlaceListVisible = true,
                selectedRegionName = regionName,
            )
        }
    }

    fun onBottomSheetDismiss() {
        _uiState.update { it.copy(isPlaceListVisible = false) }
    }

    fun onResetSelectionClick() {
        _uiState.update { it.copy(selectedPlaceList = persistentSetOf()) }
    }

    fun onCheckboxClick(placeId: Long) {
        _uiState.update { state ->
            val selectedPlaceIds = if (placeId in state.selectedPlaceList) {
                state.selectedPlaceList.remove(placeId)
            } else {
                state.selectedPlaceList.add(placeId)
            }
            state.copy(selectedPlaceList = selectedPlaceIds)
        }
    }

    fun onPlaceItemClick(placeId: Long) {
        _uiState.update { it.copy(step = Step.DETAIL, currentPlaceId = placeId) }
    }

    fun onSelectButtonClick() {
        _uiState.update { state ->
            val viewingPlaceId = state.currentPlaceId ?: return@update state.copy(step = Step.RECOMMENDATION)
            state.copy(
                step = Step.RECOMMENDATION,
                selectedPlaceList = state.selectedPlaceList.add(viewingPlaceId),
            )
        }
    }

    fun backToPlaceList() {
        _uiState.update { it.copy(step = Step.RECOMMENDATION) }
    }
}
