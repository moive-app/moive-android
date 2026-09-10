package com.moive.app.presentation.meeting.confirmed

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.model.TravelParticipantModel
import com.moive.app.data.voting.model.PlaceDetailImageItemModel
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingConfirmedContract {
    @Immutable
    data class State(
        val step: Step = Step.MAIN,
        val myProfileImageUrl: String = "",
        val meetingLink: String = "https://moive.app/meeting/1",
        val isPlaceConfirmed: Boolean = true,
        val placeName: String = "장소명(상호명)",
        val placeCategory: String = "카페",
        val placeAddress: String = "서울시 강남구 OO동",
        val meetingDate: String = "9월 18일",
        val meetingTime: String = "오후 6:00",
        val participants: ImmutableList<TravelParticipantModel> = persistentListOf(
            TravelParticipantModel(id = 1L, name = "다인", profileImageUrl = "", address = "서울시 구로구 머시기", transferCount = 1, travelMinutes = 32),
            TravelParticipantModel(id = 2L, name = "수현", profileImageUrl = "", address = "경기도 수원시 고색동", transferCount = 2, travelMinutes = 38),
            TravelParticipantModel(id = 3L, name = "민주", profileImageUrl = "", address = "경기도 수원시 OO동", transferCount = 2, travelMinutes = 52),
            TravelParticipantModel(id = 4L, name = "혜지", profileImageUrl = "", address = "서울시 OO구 머시기", transferCount = 0, travelMinutes = 48),
            TravelParticipantModel(id = 5L, name = "지민", profileImageUrl = "", address = "서울시 구로구 OO동", transferCount = 0, travelMinutes = 50),
        ),
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
        MAIN,
        DETAIL;
    }
}
