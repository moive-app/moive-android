package com.moive.app.presentation.voting

import androidx.compose.runtime.Immutable
import com.moive.app.data.voting.model.PlaceDetailImageItemModel
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.data.voting.model.PlaceRecommendationCardItemModel
import com.moive.app.data.voting.model.RegionPinModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

interface VotingContract {
    @Immutable
    data class State(
        val step: Step = Step.RECOMMENDATION,
        val recommendedAreaUiState: RecommendedAreaUiState = RecommendedAreaUiState.Idle,
        val isPlaceListVisible: Boolean = false,
        val selectedRegionName: String? = null,
        val regionList: ImmutableList<RegionPinModel> = persistentListOf(),
        val placeList: PersistentList<PlaceRecommendationCardItemModel> = persistentListOf(
            PlaceRecommendationCardItemModel(
                id = 1L,
                name = "장소명(상호명) 1",
                category = "카페",
                address = "서울시 강남구 워시기워시기 123",
                matchRate = 60,
                avgTravelMinutes = 36,
                maxTravelMinutes = 41,
                tasteMatchCount = 4,
                tasteMatchTotal = 7,
                totalTravelMinutes = 34,
                totalTravelFare = 1_650,
            ),
            PlaceRecommendationCardItemModel(
                id = 2L,
                name = "장소명(상호명) 2",
                category = "식당",
                address = "서울시 강남구 워시기워시기 456",
                matchRate = 50,
                avgTravelMinutes = 36,
                maxTravelMinutes = 41,
                tasteMatchCount = 4,
                tasteMatchTotal = 7,
                totalTravelMinutes = 34,
                totalTravelFare = 1650,
            ),
            PlaceRecommendationCardItemModel(
                id = 3L,
                name = "장소명(상호명) 3",
                category = "카페",
                address = "서울시 강남구 워시기워시기 789",
                matchRate = 45,
                avgTravelMinutes = 36,
                maxTravelMinutes = 41,
                tasteMatchCount = 4,
                tasteMatchTotal = 7,
                totalTravelMinutes = 34,
                totalTravelFare = 1650,
            ),
        ),
        val selectedPlaceList: PersistentSet<Long> = persistentSetOf(),
        val currentPlaceId: Long? = null,
        val currentPlaceDetail: PlaceDetailModel = PlaceDetailModel(
            id = 1L,
            userName = "모이브",
            placeName = "장소명(상호명)",
            category = "카페",
            address = "서울시 강남구 워시기워시기 123",
            totalMemberCount = 7,
            matchMemberCount = 4,
            imageList = listOf(
                PlaceDetailImageItemModel(id = 1L, imageUrl = ""),
                PlaceDetailImageItemModel(id = 2L, imageUrl = ""),
                PlaceDetailImageItemModel(id = 3L, imageUrl = ""),
            ),
            startPinLatLang = PlaceDetailPinLatLang(
                latitude = 37.5044,
                longitude = 127.0246,
            ),
            endPinLatLang = PlaceDetailPinLatLang(
                latitude = 37.5089,
                longitude = 127.0632,
            ),
            routeLatLang = PlaceDetailRouteLatLang(
                latitude = listOf(
                    37.5044, 37.5054217, 37.505525, 37.5049788, 37.50665,
                    37.5083212, 37.507775, 37.5078783, 37.5089,
                ),
                longitude = listOf(
                    127.0246, 127.0291954, 127.03425, 127.0396293, 127.0439,
                    127.0481707, 127.05355, 127.0586046, 127.0632,
                ),
            ),
            totalTravelMinutes = 34,
            avgTravelMinutes = 36,
            walkMinutes = 10,
            busMinutes = 15,
            subwayMinutes = 9,
            travelFare = 1_650,
        ),
    )

    enum class Step {
        RECOMMENDATION,
        DETAIL;
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
