package com.moive.app.presentation.meeting.list

import androidx.compose.runtime.Immutable
import com.moive.app.data.home.mapper.MeetingTab
import com.moive.app.data.meeting.model.MeetingListCardItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingListContract {
    @Immutable
    data class State(
        val selectedTab: String = MeetingTab.ALL.label,
        val meetingListUiState: MeetingListUiState = MeetingListUiState.Idle,
        val meetingList: ImmutableList<MeetingListCardItemModel> = persistentListOf(),
        val nextCursor: Long? = null,
        val hasNextMeetingList: Boolean = true,
    ) {
        val tabList: ImmutableList<String> = persistentListOf(
            MeetingTab.ALL.label,
            MeetingTab.UPCOMING.label,
            MeetingTab.PAST.label,
        )
    }
}

sealed interface MeetingListUiState {
    data object Idle : MeetingListUiState
    data object Loading : MeetingListUiState
    data object Success : MeetingListUiState
    data class Failure(
        val msg: String,
    ) : MeetingListUiState
}
