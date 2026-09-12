package com.moive.app.presentation.voting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.data.voting.repository.VotingRepository
import com.moive.app.presentation.voting.VotingContract.Step
import com.moive.app.presentation.voting.navigation.Voting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VotingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val votingRepository: VotingRepository,
) : ViewModel() {

    private val meetingId: Long = savedStateHandle.toRoute<Voting>().meetingId

    private val _uiState = MutableStateFlow(VotingContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        getRecommendedAreas()
    }

    private fun getRecommendedAreas() = viewModelScope.launch {
        _uiState.update { it.copy(recommendedAreaUiState = RecommendedAreaUiState.Loading) }

        votingRepository.getRecommendedAreas(meetingId)
            .onSuccess { regions ->
                _uiState.update {
                    it.copy(
                        recommendedAreaUiState = RecommendedAreaUiState.Success,
                        regionList = regions.toImmutableList(),
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error, RECOMMENDED_AREA_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        recommendedAreaUiState = RecommendedAreaUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE,),
                    )
                }
            }
    }

    fun onRegionPinClick(region: RegionPinModel) {
        _uiState.update {
            it.copy(
                isPlaceListVisible = true,
                selectedRegionId = region.id,
                selectedRegionName = region.name,
            )
        }
        getRecommendedPlaces(region.id)
    }

    private fun getRecommendedPlaces(recommendedAreaId: Long) = viewModelScope.launch {
        _uiState.update { it.copy(recommendedPlaceUiState = RecommendedPlaceUiState.Loading) }

        votingRepository.getRecommendedPlaces(meetingId, recommendedAreaId)
            .onSuccess { places ->
                _uiState.update {
                    it.copy(
                        recommendedPlaceUiState = RecommendedPlaceUiState.Success,
                        placeList = places.toImmutableList(),
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error, RECOMMENDED_PLACE_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        recommendedPlaceUiState = RecommendedPlaceUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
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
        getRecommendedPlaceDetail(placeId)
    }

    private fun getRecommendedPlaceDetail(placeId: Long) = viewModelScope.launch {
        val recommendedAreaId = _uiState.value.selectedRegionId ?: return@launch

        _uiState.update { it.copy(recommendedPlaceDetailUiState = RecommendedPlaceDetailUiState.Loading) }

        votingRepository.getRecommendedPlaceDetail(meetingId, recommendedAreaId, placeId)
            .onSuccess { detail ->
                _uiState.update {
                    it.copy(
                        recommendedPlaceDetailUiState = RecommendedPlaceDetailUiState.Success,
                        currentPlaceDetail = it.currentPlaceDetail.copy(
                            id = detail.id,
                            placeName = detail.name,
                            category = detail.category,
                            address = detail.address,
                            matchMemberCount = detail.preferenceMatchCnt,
                            avgTravelMinutes = detail.averageTravelTime,
                            imageList = detail.imageUrls,
                        ),
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error, RECOMMENDED_PLACE_DETAIL_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        recommendedPlaceDetailUiState = RecommendedPlaceDetailUiState.Failure(
                            error.message ?: UNKNOWN_ERROR_MESSAGE,
                        ),
                    )
                }
            }
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

    fun onKakaoMapRouteOpened(opened: Boolean) {
        if (opened) return
        Timber.tag(TAG).e(KAKAO_MAP_ERROR)
    }

    companion object {
        private const val TAG = "Voting"
        private const val KAKAO_MAP_ERROR = "카카오 맵을 열 수 없습니다."
        private const val RECOMMENDED_AREA_FAILURE_MESSAGE = "추천 지역 조회에 실패했습니다."
        private const val RECOMMENDED_PLACE_FAILURE_MESSAGE = "추천 장소 조회에 실패했습니다."
        private const val RECOMMENDED_PLACE_DETAIL_FAILURE_MESSAGE = "추천 장소 상세 조회에 실패했습니다."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
