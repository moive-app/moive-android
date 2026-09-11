package com.moive.app.presentation.meeting.list

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.data.home.mapper.MeetingTab
import com.moive.app.data.meeting.model.MeetingListCardItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingListContract {
    @Immutable
    data class State(
        val selectedTab: String = MeetingTab.ALL.label,
        val meetingList: ImmutableList<MeetingListCardItemModel> = persistentListOf(
            MeetingListCardItemModel(
                id = 1L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "조건 입력중",
                statusLabelType = LabelType.CONDITION,
            ),
            MeetingListCardItemModel(
                id = 2L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "투표 진행중",
                statusLabelType = LabelType.VOTING,
            ),
            MeetingListCardItemModel(
                id = 3L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "모임 확정",
                statusLabelType = LabelType.CONFIRMED,
            ),
            MeetingListCardItemModel(
                id = 4L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "모임 완료",
                statusLabelType = LabelType.COMPLETE,
            ),
            MeetingListCardItemModel(
                id = 5L,
                title = "주말 맛집 모임",
                dateTime = "8월 29일 14:00",
                participantImageUrls = persistentListOf("", "", ""),
                extraParticipantCount = 2,
                statusText = "조건 입력중",
                statusLabelType = LabelType.CONDITION,
            ),
        ),
    ) {
        val tabList: ImmutableList<String> = persistentListOf(
            MeetingTab.ALL.label,
            MeetingTab.UPCOMING.label,
            MeetingTab.PAST.label,
        )
    }
}
