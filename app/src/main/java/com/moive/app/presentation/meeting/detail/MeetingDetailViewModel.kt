package com.moive.app.presentation.meeting.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.moive.app.data.meeting.repository.MeetingRepository
import com.moive.app.presentation.meeting.detail.MeetingDetailContract.SideEffect
import com.moive.app.presentation.meeting.detail.navigation.MeetingDetail
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
class MeetingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val meetingRepository: MeetingRepository,
) : ViewModel() {

    private val meetingId: Long = savedStateHandle.toRoute<MeetingDetail>().meetingId

    private val _uiState = MutableStateFlow(MeetingDetailContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var getMeetingDetailJob: Job? = null
    private var deleteMeetingJob: Job? = null

    fun getMeetingDetail() {
        getMeetingDetailJob?.cancel()
        getMeetingDetailJob = viewModelScope.launch {
            _uiState.update { it.copy(meetingDetailUiState = MeetingDetailUiState.Loading) }

            meetingRepository.getMeetingDetail(meetingId)
                .onSuccess { detail ->
                    _uiState.update {
                        it.copy(
                            meetingDetailUiState = MeetingDetailUiState.Success,
                            status = detail.status,
                            hasSchedule = detail.hasSchedule,
                            scheduledDate = detail.scheduledDate,
                            scheduledTime = detail.scheduledTime,
                            meetingName = detail.name,
                            meetingPurpose = detail.purposeType.label,
                            inviteCode = detail.inviteCode,
                            inviteUrl = detail.inviteUrl,
                            participants = detail.participants,
                            toolTipMessage = detail.homeMessage,
                            primaryActionLabel = detail.primaryActionLabel,
                            primaryActionEnabled = detail.primaryActionEnabled,
                        )
                    }
                }
                .onFailure { error ->
                    Timber.tag(TAG).e(error)
                    _uiState.update {
                        it.copy(meetingDetailUiState = MeetingDetailUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE))
                    }
                }
        }
    }

    fun showLeaveMeetingDialog() {
        _uiState.update { it.copy(isLeaveMeetingDialogVisible = true) }
    }

    fun dismissLeaveMeetingDialog() {
        _uiState.update { it.copy(isLeaveMeetingDialogVisible = false) }
    }

    fun deleteMeeting() {
        if (deleteMeetingJob?.isActive == true) return
        deleteMeetingJob = viewModelScope.launch {
            meetingRepository.deleteMeeting(meetingId)
                .onSuccess {
                    _sideEffect.send(SideEffect.NavigateBack)
                }
                .onFailure { error ->
                    Timber.tag(TAG).e(error)
                }
        }
    }

    companion object {
        private const val TAG = "MeetingDetail"
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
