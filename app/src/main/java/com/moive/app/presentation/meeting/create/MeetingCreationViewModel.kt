package com.moive.app.presentation.meeting.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MeetingCreationViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingCreationContract.State())
    val uiState = _uiState.asStateFlow()

    fun toggleScheduleConfirmed(isConfirmed: String) =
        _uiState.update {
            it.copy(selectedScheduleConfirmed = isConfirmed)
        }

    fun toggleMeetingPurpose(purpose: String) =
        _uiState.update {
            it.copy(selectedMeetingPurpose = purpose)
        }

}
