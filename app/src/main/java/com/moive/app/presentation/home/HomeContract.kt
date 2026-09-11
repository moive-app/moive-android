package com.moive.app.presentation.home

import androidx.compose.runtime.Immutable
import com.moive.app.data.home.mapper.MeetingTab
import com.moive.app.data.home.model.MyMeetingCardItemModel
import com.moive.app.data.home.model.ConfirmedMeetingItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface HomeContract {
    @Immutable
    data class State(
        val userName: String = "수현",
        val isAlarmUnRead: Boolean = false,
        val selectedTab: String = MeetingTab.ALL.label,
        val homeUiState: HomeUiState = HomeUiState.Idle,
        val myMeetingList: ImmutableList<MyMeetingCardItemModel> = persistentListOf(),
        val upcomingMeetings: ImmutableList<ConfirmedMeetingItemModel> = persistentListOf(),
    ) {
        val tabList: ImmutableList<String> = persistentListOf(
            MeetingTab.ALL.label,
            MeetingTab.UPCOMING.label,
            MeetingTab.PAST.label,
        )
    }
}

sealed interface HomeUiState {
    data object Idle : HomeUiState
    data object Loading : HomeUiState
    data object Success : HomeUiState
    data class Failure(
        val msg: String,
    ) : HomeUiState
}
