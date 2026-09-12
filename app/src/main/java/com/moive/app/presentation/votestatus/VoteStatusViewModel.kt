package com.moive.app.presentation.votestatus

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.data.voting.repository.VotingRepository
import com.moive.app.data.votingstatus.repository.VoteStatusRepository
import com.moive.app.presentation.votestatus.VoteStatusContract.Step
import com.moive.app.presentation.votestatus.navigation.VoteStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
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
    private val votingRepository: VotingRepository,
) : ViewModel() {

    private val meetingId: Long = savedStateHandle.toRoute<VoteStatus>().meetingId

    private val _uiState = MutableStateFlow(VoteStatusContract.State())
    val uiState = _uiState.asStateFlow()

    private var placeDetailJob: Job? = null

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
        placeDetailJob?.cancel()

        val candidate = _uiState.value.placeCandidates.firstOrNull { it.id == placeId }

        _uiState.update {
            it.copy(
                step = Step.DETAIL,
                currentPlaceId = placeId,
                currentPlaceDetail = it.currentPlaceDetail.copy(
                    id = placeId,
                    placeName = candidate?.placeName ?: UNKNOWN_PLACE_NAME,
                    userName = "",
                    category = "",
                    address = "",
                    areaName = "",
                    totalMemberCount = 0,
                    matchMemberCount = 0,
                    imageList = emptyList(),
                    startPinLatLang = PlaceDetailPinLatLang(latitude = 0.0, longitude = 0.0),
                    endPinLatLang = PlaceDetailPinLatLang(latitude = 0.0, longitude = 0.0),
                    routeLatLang = PlaceDetailRouteLatLang(latitude = listOf(0.0, 0.0), longitude = listOf(0.0, 0.0)),
                    totalTravelMinutes = 0,
                    avgTravelMinutes = 0,
                    walkMinutes = 0,
                    busMinutes = 0,
                    subwayMinutes = 0,
                    travelFare = 0,
                    landingUrl = "",
                ),
            )
        }

        val areaId = candidate?.areaId ?: return

        placeDetailJob = viewModelScope.launch {
            fetchPlaceDetail(areaId, placeId)
            fetchPlaceRoute(placeId)
        }
    }

    private suspend fun fetchPlaceDetail(areaId: Long, placeId: Long) {
        _uiState.update { it.copy(placeDetailUiState = PlaceDetailUiState.Loading) }

        votingRepository.getRecommendedPlaceDetail(
            meetingId = meetingId,
            recommendedAreaId = areaId,
            recommendedPlaceId = placeId,
            current = _uiState.value.currentPlaceDetail,
        )
            .onSuccess { updated ->
                if (_uiState.value.currentPlaceId != placeId) return@onSuccess

                _uiState.update {
                    it.copy(
                        placeDetailUiState = PlaceDetailUiState.Success,
                        currentPlaceDetail = updated,
                    )
                }
            }
            .onFailure { error ->
                if (_uiState.value.currentPlaceId != placeId) return@onFailure

                Timber.tag(TAG).e(error, PLACE_DETAIL_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        placeDetailUiState = PlaceDetailUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
            }
    }

    private suspend fun fetchPlaceRoute(placeId: Long) {
        _uiState.update { it.copy(placeRouteUiState = PlaceRouteUiState.Loading) }

        votingRepository.getRecommendedPlaceRoute(
            meetingId = meetingId,
            recommendedPlaceId = placeId,
            current = _uiState.value.currentPlaceDetail,
        )
            .onSuccess { updated ->
                if (_uiState.value.currentPlaceId != placeId) return@onSuccess

                _uiState.update {
                    it.copy(
                        placeRouteUiState = PlaceRouteUiState.Success,
                        currentPlaceDetail = updated,
                    )
                }
            }
            .onFailure { error ->
                if (_uiState.value.currentPlaceId != placeId) return@onFailure

                Timber.tag(TAG).e(error, PLACE_ROUTE_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        placeRouteUiState = PlaceRouteUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
            }
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
        private const val PLACE_DETAIL_FAILURE_MESSAGE = "장소 상세 조회에 실패했습니다."
        private const val PLACE_ROUTE_FAILURE_MESSAGE = "이동 경로 조회에 실패했습니다."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
        private const val UNKNOWN_PLACE_NAME = "알 수 없는 장소"
    }
}
