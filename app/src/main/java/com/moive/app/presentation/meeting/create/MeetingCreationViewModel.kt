package com.moive.app.presentation.meeting.create

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import com.moive.app.core.extensions.trim
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

    fun toggleScheduleConfirmed(isConfirmed: String) {
        _uiState.update {
            it.copy(selectedScheduleConfirmed = isConfirmed)
        }

        if (isConfirmed == SCHEDULE_NOT_CONFIRMED) {
            _uiState.value.meetingSchedule.clearText()
        }
    }

    fun toggleMeetingPurpose(purpose: String) =
        _uiState.update {
            it.copy(selectedMeetingPurpose = purpose)
        }

    fun trimMeetingName() {
        _uiState.value.meetingName.trim()
    }

    fun moveToConfirmStep() {
        _uiState.update { it.copy(step = MeetingCreationContract.Step.CONFIRM) }
    }

    fun backToCreateStep() {
        _uiState.update { it.copy(step = MeetingCreationContract.Step.CREATE) }
    }

    companion object {
        private const val SCHEDULE_NOT_CONFIRMED = "아니오"
    }

}
