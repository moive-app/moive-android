package com.moive.app.presentation.home

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.data.home.model.MyMeetingCardItemModel
import com.moive.app.data.home.model.ConfirmedMeetingItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface HomeContract {
    @Immutable
    data class State(
        val userName: String = "",
        val isAlarmUnRead: Boolean = false,
        val selectedTab: String = MeetingTab.ALL.label,
        val myMeetingList: ImmutableList<MyMeetingCardItemModel> = persistentListOf(
            MyMeetingCardItemModel(
                id = 1L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "조건 입력중",
                statusLabelType = LabelType.CONDITION,
            ),
            MyMeetingCardItemModel(
                id = 2L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "투표 진행중",
                statusLabelType = LabelType.VOTING,
            ),
            MyMeetingCardItemModel(
                id = 3L,
                title = "강남에서 만나자",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "모임 확정",
                statusLabelType = LabelType.CONFIRMED,
            ),
        ),
        val upcomingMeetings: ImmutableList<ConfirmedMeetingItemModel> = persistentListOf(
            ConfirmedMeetingItemModel(
                id = 1L,
                title = "강남에서 만나자",
                dateTime = "8월 29일 14:00",
                location = "홍대입구역 2번 출구",
                participantImageList = persistentListOf("", "", ""),
                extraCount = 2,
                dDayText = "D-5",
            ),
            ConfirmedMeetingItemModel(
                id = 2L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                location = "홍대입구역 2번 출구",
                participantImageList = persistentListOf("", ""),
                extraCount = 1,
                dDayText = "D-3",
            ),
            ConfirmedMeetingItemModel(
                id = 1L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                location = "홍대입구역 2번 출구",
                participantImageList = persistentListOf("", ""),
                extraCount = 1,
                dDayText = "D-3",
            ),
        ),
    ) {
        val tabList: ImmutableList<String> = persistentListOf(
            MeetingTab.ALL.label,
            MeetingTab.UPCOMING.label,
            MeetingTab.PAST.label,
        )
    }

    enum class MeetingTab(val label: String) {
        ALL("전체"),
        UPCOMING("예정"),
        PAST("지난 모임"),
    }
}
