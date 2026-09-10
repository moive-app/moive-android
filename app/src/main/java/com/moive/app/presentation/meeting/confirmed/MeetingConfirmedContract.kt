package com.moive.app.presentation.meeting.confirmed

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.model.TravelParticipantModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingConfirmedContract {
    @Immutable
    data class State(
        val myProfileImageUrl: String = "",
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
    )
}
