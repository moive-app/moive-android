package com.moive.app.presentation.voting

import androidx.compose.runtime.Immutable
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.data.voting.model.PlaceRecommendedPlaceCardItemModel
import com.moive.app.data.voting.model.RegionPinModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

interface VotingContract {
    @Immutable
    data class State(
        val step: Step = Step.RECOMMENDATION,
        val recommendedAreaUiState: RecommendedAreaUiState = RecommendedAreaUiState.Idle,
        val recommendedPlaceUiState: RecommendedPlaceUiState = RecommendedPlaceUiState.Idle,
        val recommendedPlaceDetailUiState: RecommendedPlaceDetailUiState = RecommendedPlaceDetailUiState.Idle,
        val recommendedPlaceRouteUiState: RecommendedPlaceRouteUiState = RecommendedPlaceRouteUiState.Idle,
        val placeVoteUiState: PlaceVoteUiState = PlaceVoteUiState.Idle,
        val isPlaceListVisible: Boolean = false,
        val selectedRegionId: Long? = null,
        val selectedRegionName: String? = null,
        val regionList: ImmutableList<RegionPinModel> = persistentListOf(),
        val placeList: ImmutableList<PlaceRecommendedPlaceCardItemModel> = persistentListOf(),
        val selectedPlaceList: PersistentSet<Long> = persistentSetOf(),
        val currentPlaceId: Long? = null,
        val currentPlaceDetail: PlaceDetailModel = PlaceDetailModel(
            id = 1L,
            userName = "",
            placeName = "",
            category = "",
            address = "",
            areaName = "",
            totalMemberCount = 0,
            matchMemberCount = 0,
            imageList = emptyList(),
            startPinLatLang = PlaceDetailPinLatLang(
                latitude = 0.0,
                longitude = 0.0,
            ),
            endPinLatLang = PlaceDetailPinLatLang(
                latitude = 0.0,
                longitude = 0.0,
            ),
            routeLatLang = PlaceDetailRouteLatLang(
                latitude = listOf(0.0, 0.0),
                longitude = listOf(0.0, 0.0),
            ),
            totalTravelMinutes = 0,
            avgTravelMinutes = 0,
            walkMinutes = 0,
            busMinutes = 0,
            subwayMinutes = 0,
            travelFare = 0,
        ),
    )

    enum class Step {
        RECOMMENDATION,
        DETAIL;
    }

    sealed class SideEffect {
        data class NavigateToVoteStatus(val meetingId: Long) : SideEffect()
    }
}

sealed interface RecommendedAreaUiState {
    data object Idle : RecommendedAreaUiState
    data object Loading : RecommendedAreaUiState
    data object Success : RecommendedAreaUiState
    data class Failure(
        val msg: String,
    ) : RecommendedAreaUiState
}

sealed interface RecommendedPlaceUiState {
    data object Idle : RecommendedPlaceUiState
    data object Loading : RecommendedPlaceUiState
    data object Success : RecommendedPlaceUiState
    data class Failure(
        val msg: String,
    ) : RecommendedPlaceUiState
}

sealed interface RecommendedPlaceDetailUiState {
    data object Idle : RecommendedPlaceDetailUiState
    data object Loading : RecommendedPlaceDetailUiState
    data object Success : RecommendedPlaceDetailUiState
    data class Failure(
        val msg: String,
    ) : RecommendedPlaceDetailUiState
}

sealed interface RecommendedPlaceRouteUiState {
    data object Idle : RecommendedPlaceRouteUiState
    data object Loading : RecommendedPlaceRouteUiState
    data object Success : RecommendedPlaceRouteUiState
    data class Failure(
        val msg: String,
    ) : RecommendedPlaceRouteUiState
}

sealed interface PlaceVoteUiState {
    data object Idle : PlaceVoteUiState
    data object Loading : PlaceVoteUiState
    data object Success : PlaceVoteUiState
    data class Failure(
        val msg: String,
    ) : PlaceVoteUiState
}
