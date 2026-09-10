package com.moive.app.presentation.meeting.complete

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.model.CompletedParticipantItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingCompleteContract {
    @Immutable
    data class State(
        val isPlaceConfirmed: Boolean = true,
        val placeName: String = "장소명(상호명)",
        val placeCategory: String = "카페",
        val placeAddress: String = "서울시 강남구 OO동",
        val meetingDate: String = "9월 18일",
        val meetingTime: String = "오후 6:00",
        val participants: ImmutableList<CompletedParticipantItemModel> = persistentListOf(
            CompletedParticipantItemModel(id = 1L, name = "다인", profileImageUrl = "", address = "서울시 구로구 머시기"),
            CompletedParticipantItemModel(id = 2L, name = "수현", profileImageUrl = "", address = "경기도 수원시 고색동"),
            CompletedParticipantItemModel(id = 3L, name = "민주", profileImageUrl = "", address = "경기도 수원시 OO동"),
            CompletedParticipantItemModel(id = 4L, name = "혜지", profileImageUrl = "", address = "서울시 구로구 머시기"),
            CompletedParticipantItemModel(id = 5L, name = "지민", profileImageUrl = "", address = "서울시 구로구 머시기"),
        ),
    )
}
