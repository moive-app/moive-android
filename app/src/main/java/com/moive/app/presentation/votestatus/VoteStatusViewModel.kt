package com.moive.app.presentation.votestatus

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.moive.app.data.votingstatus.repository.VoteStatusRepository
import com.moive.app.presentation.votestatus.VoteStatusContract.Step
import com.moive.app.presentation.votestatus.navigation.VoteStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VoteStatusViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val voteStatusRepository: VoteStatusRepository,
) : ViewModel() {

    private val meetingId: Long = savedStateHandle.toRoute<VoteStatus>().meetingId

    private val _uiState = MutableStateFlow(VoteStatusContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        getScheduleVoteResult()
        getPlaceVoteResult()
    }

    private fun getScheduleVoteResult() = viewModelScope.launch {
        _uiState.update { it.copy(scheduleVoteResultUiState = ScheduleVoteResultUiState.Loading) }

        voteStatusRepository.getScheduleVoteResult(meetingId)
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        scheduleVoteResultUiState = ScheduleVoteResultUiState.Success,
                        isScheduleVoteSkipped = result.isVoteSkipped,
                        scheduleTotalVoterCount = result.totalVoterCount,
                        scheduleCandidates = result.candidates.toImmutableList(),
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error, SCHEDULE_VOTE_RESULT_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        scheduleVoteResultUiState = ScheduleVoteResultUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
            }
    }

    private fun getPlaceVoteResult() = viewModelScope.launch {
        _uiState.update { it.copy(placeVoteResultUiState = PlaceVoteResultUiState.Loading) }

        voteStatusRepository.getPlaceVoteResult(meetingId)
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        placeVoteResultUiState = PlaceVoteResultUiState.Success,
                        isPlaceVoteFinished = result.isFinished,
                        placeTotalVoterCount = result.totalVoterCount,
                        placeCandidates = result.candidates.toImmutableList(),
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error, PLACE_VOTE_RESULT_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        placeVoteResultUiState = PlaceVoteResultUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
            }
    }

    fun onPlaceItemClick(placeId: Long) {
        _uiState.update { it.copy(step = Step.DETAIL, currentPlaceId = placeId) }
    }

    fun backToList() {
        _uiState.update { it.copy(step = Step.LIST) }
    }

    fun onKakaoMapRouteOpened(opened: Boolean) {
        if (opened) return
        Timber.tag(TAG).e(KAKAO_MAP_ERROR)
    }

    companion object {
        private const val TAG = "VoteStatus"
        private const val KAKAO_MAP_ERROR = "카카오 맵을 열 수 없습니다."
        private const val SCHEDULE_VOTE_RESULT_FAILURE_MESSAGE = "일정 투표 현황 조회에 실패했습니다."
        private const val PLACE_VOTE_RESULT_FAILURE_MESSAGE = "장소 투표 현황 조회에 실패했습니다."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
