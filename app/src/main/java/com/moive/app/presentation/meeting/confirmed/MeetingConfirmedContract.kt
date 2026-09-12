package com.moive.app.presentation.meeting.confirmed

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.model.MeetingResultParticipantModel
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingConfirmedContract {
    @Immutable
    data class State(
        val step: Step = Step.MAIN,
        val meetingResultUiState: MeetingResultUiState = MeetingResultUiState.Idle,
        val myProfileImageUrl: String = "",
        val meetingLink: String = "",
        val isPlaceConfirmed: Boolean = true,
        val placeName: String = "",
        val placeCategory: String = "",
        val placeAddress: String = "",
        val meetingDate: String = "",
        val meetingTime: String = "",
        val participants: ImmutableList<MeetingResultParticipantModel> = persistentListOf(
            MeetingResultParticipantModel(id = 1L, name = "", profileImageUrl = "", address = "", transferCount = 0, travelMinutes = 0),
            MeetingResultParticipantModel(id = 2L, name = "", profileImageUrl = "", address = "", transferCount = 0, travelMinutes = 0),
            MeetingResultParticipantModel(id = 3L, name = "", profileImageUrl = "", address = "", transferCount = 0, travelMinutes = 0),
            MeetingResultParticipantModel(id = 4L, name = "", profileImageUrl = "", address = "", transferCount = 0, travelMinutes = 0),
            MeetingResultParticipantModel(id = 5L, name = "", profileImageUrl = "", address = "", transferCount = 0, travelMinutes = 0),
        ),
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
        MAIN,
        DETAIL;
    }
}

sealed interface MeetingResultUiState {
    data object Idle : MeetingResultUiState
    data object Loading : MeetingResultUiState
    data object Success : MeetingResultUiState
    data class Failure(
        val msg: String,
    ) : MeetingResultUiState
}
