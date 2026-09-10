package com.moive.app.presentation.votestatus

import androidx.compose.runtime.Immutable
import com.moive.app.data.voting.model.PlaceDetailImageItemModel
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
        val isScheduleVoteSkipped: Boolean = false,
        val scheduleTotalVoterCount: Int? = 5,
        val scheduleCandidates: ImmutableList<ScheduleVoteCandidateModel> = persistentListOf(
            ScheduleVoteCandidateModel(meetingDate = "2026-09-12", meetingTime = "18:00", voterCount = 4, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "2026-09-08", meetingTime = "18:00", voterCount = 3, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "2026-09-10", meetingTime = "18:00", voterCount = 3, isVotedByMe = false),
        ),
        val placeTotalVoterCount: Int = 4,
        val placeCandidates: ImmutableList<PlaceVoteCandidateModel> = persistentListOf(
            PlaceVoteCandidateModel(id = 1L, placeName = "OOO 맛집", voterCount = 4, isVotedByMe = true),
            PlaceVoteCandidateModel(id = 2L, placeName = "XXX 카페", voterCount = 3, isVotedByMe = true),
            PlaceVoteCandidateModel(id = 3L, placeName = "ΔΔΔ 이자카야", voterCount = 2, isVotedByMe = false),
        ),
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
