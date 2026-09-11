package com.moive.app.presentation.meeting.create

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.core.extensions.trim
import com.moive.app.data.meeting.mapper.MeetingPurposeType
import com.moive.app.data.meeting.repository.MeetingRepository
import com.moive.app.presentation.meeting.create.MeetingCreationContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MeetingCreationViewModel @Inject constructor(
    private val meetingRepository: MeetingRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingCreationContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var job: Job? = null

    fun toggleScheduleConfirmed(isConfirmed: String) {
        _uiState.update {
            it.copy(selectedScheduleConfirmed = isConfirmed)
        }

        if (isConfirmed == MeetingCreationContract.SCHEDULE_NOT_CONFIRMED) {
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

    fun postMeetingCreation() {
        if (job?.isActive == true) return

        val currentState = _uiState.value

        val hasSchedule = currentState.selectedScheduleConfirmed == MeetingCreationContract.SCHEDULE_CONFIRMED
        val (scheduledDate, scheduledTime) = if (hasSchedule) {
            val schedule = currentState.meetingSchedule.text.toString().toScheduledDateTime()
            if (schedule == null) {
                Timber.tag(MEETING_CREATION_TAG).e(CREATE_MEETING_FAILURE_MESSAGE)
                return
            }
            schedule
        } else {
            null to null
        }

        job = viewModelScope.launch {
            _uiState.update { it.copy(meetingCreationUiState = MeetingCreationUiState.Loading) }

            meetingRepository.postMeetingCreation(
                name = currentState.meetingName.text.toString(),
                hasSchedule = hasSchedule,
                scheduledDate = scheduledDate,
                scheduledTime = scheduledTime,
                purposeType = currentState.selectedMeetingPurpose.toMeetingPurposeType() ?: MeetingPurposeType.ETC,
            )
                .onSuccess {
                    _sideEffect.send(SideEffect.NavigateToMeetingDetail)
                    _uiState.update {
                        it.copy(meetingCreationUiState = MeetingCreationUiState.Success)
                    }
                }
                .onFailure { error ->
                    Timber.tag(MEETING_CREATION_TAG).e(error, CREATE_MEETING_FAILURE_MESSAGE)
                    _uiState.update {
                        it.copy(meetingCreationUiState = MeetingCreationUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE))
                    }
                }
        }
    }

    companion object {
        private const val MEETING_CREATION_TAG = "MeetingCreation"
        private const val CREATE_MEETING_FAILURE_MESSAGE = "모임 생성에 실패했습니다."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
