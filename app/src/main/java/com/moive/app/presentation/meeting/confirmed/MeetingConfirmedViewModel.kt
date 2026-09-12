package com.moive.app.presentation.meeting.confirmed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.moive.app.core.extensions.parseDate
import com.moive.app.core.extensions.parseTime
import com.moive.app.data.meeting.repository.MeetingRepository
import com.moive.app.presentation.meeting.confirmed.MeetingConfirmedContract.Step
import com.moive.app.presentation.meeting.confirmed.navigation.MeetingConfirmed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MeetingConfirmedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val meetingRepository: MeetingRepository,
) : ViewModel() {

    private val meetingId: Long = savedStateHandle.toRoute<MeetingConfirmed>().meetingId

    private val _uiState = MutableStateFlow(MeetingConfirmedContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        getMeetingResult()
    }

    private fun getMeetingResult() = viewModelScope.launch {
        _uiState.update { it.copy(meetingResultUiState = MeetingResultUiState.Loading) }

        meetingRepository.getMeetingResult(meetingId)
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        meetingResultUiState = MeetingResultUiState.Success,
                        isPlaceConfirmed = result.place != null,
                        placeName = result.place?.name ?: it.placeName,
                        placeCategory = result.place?.category ?: it.placeCategory,
                        placeAddress = result.place?.address ?: it.placeAddress,
                        meetingDate = result.meetingDate.parseDate(),
                        meetingTime = result.meetingTime.parseTime(),
                        participants = result.participants.toImmutableList(),
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error, MEETING_RESULT_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        meetingResultUiState = MeetingResultUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
            }
    }

    fun onPlaceClick() {
        _uiState.update { it.copy(step = Step.DETAIL) }
    }

    fun backToMain() {
        _uiState.update { it.copy(step = Step.MAIN) }
    }

    fun onKakaoMapRouteOpened(opened: Boolean) {
        if (opened) return
        Timber.tag(TAG).e(KAKAO_MAP_ERROR)
    }

    companion object {
        private const val TAG = "MeetingConfirmed"
        private const val KAKAO_MAP_ERROR = "카카오 맵을 열 수 없습니다."
        private const val MEETING_RESULT_FAILURE_MESSAGE = "모임 상세 조회에 실패했습니다."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
