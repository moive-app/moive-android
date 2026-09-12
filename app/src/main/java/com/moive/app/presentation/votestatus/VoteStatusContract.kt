package com.moive.app.presentation.votestatus

import androidx.compose.runtime.Immutable
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.data.votingstatus.model.PlaceVoteCandidateModel
import com.moive.app.data.votingstatus.model.ScheduleVoteCandidateModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface VoteStatusContract {
    @Immutable
    data class State(
        val step: Step = Step.LIST,
        val scheduleVoteResultUiState: ScheduleVoteResultUiState = ScheduleVoteResultUiState.Idle,
        val isScheduleVoteSkipped: Boolean = false,
        val scheduleTotalVoterCount: Int? = 5,
        val scheduleCandidates: ImmutableList<ScheduleVoteCandidateModel> = persistentListOf(
            ScheduleVoteCandidateModel(meetingDate = "", meetingTime = "", voterCount = 0, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "", meetingTime = "", voterCount = 0, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "", meetingTime = "", voterCount = 0, isVotedByMe = false),
        ),
        val placeVoteResultUiState: PlaceVoteResultUiState = PlaceVoteResultUiState.Idle,
        val isPlaceVoteFinished: Boolean = false,
        val placeTotalVoterCount: Int = 4,
        val placeCandidates: ImmutableList<PlaceVoteCandidateModel> = persistentListOf(
            PlaceVoteCandidateModel(id = 1L, areaId = 1L, placeName = "", voterCount = 0, isVotedByMe = true),
            PlaceVoteCandidateModel(id = 2L, areaId = 1L, placeName = "", voterCount = 0, isVotedByMe = true),
            PlaceVoteCandidateModel(id = 3L, areaId = 1L, placeName = "", voterCount = 0, isVotedByMe = false),
        ),
        val placeDetailUiState: PlaceDetailUiState = PlaceDetailUiState.Idle,
        val placeRouteUiState: PlaceRouteUiState = PlaceRouteUiState.Idle,
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
    ) {
        val scheduleTopVoterCount: Int
            get() = scheduleCandidates.maxOfOrNull { it.voterCount ?: 0 } ?: 0

        val confirmedScheduleCandidate: ScheduleVoteCandidateModel
            get() = scheduleCandidates.first()

        val placeTopVoterCount: Int
            get() = placeCandidates.maxOfOrNull { it.voterCount } ?: 0
    }

    enum class Step {
        LIST,
        DETAIL;
    }
}

sealed interface ScheduleVoteResultUiState {
    data object Idle : ScheduleVoteResultUiState
    data object Loading : ScheduleVoteResultUiState
    data object Success : ScheduleVoteResultUiState
    data class Failure(
        val msg: String,
    ) : ScheduleVoteResultUiState
}

sealed interface PlaceVoteResultUiState {
    data object Idle : PlaceVoteResultUiState
    data object Loading : PlaceVoteResultUiState
    data object Success : PlaceVoteResultUiState
    data class Failure(
        val msg: String,
    ) : PlaceVoteResultUiState
}

sealed interface PlaceRouteUiState {
    data object Idle : PlaceRouteUiState
    data object Loading : PlaceRouteUiState
    data object Success : PlaceRouteUiState
    data class Failure(
        val msg: String,
    ) : PlaceRouteUiState
}

sealed interface PlaceDetailUiState {
    data object Idle : PlaceDetailUiState
    data object Loading : PlaceDetailUiState
    data object Success : PlaceDetailUiState
    data class Failure(
        val msg: String,
    ) : PlaceDetailUiState
}
