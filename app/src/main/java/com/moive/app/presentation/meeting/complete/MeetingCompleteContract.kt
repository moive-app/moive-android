package com.moive.app.presentation.meeting.complete

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.model.CompletedParticipantItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingCompleteContract {
    @Immutable
    data class State(
        val meetingResultUiState: MeetingResultUiState = MeetingResultUiState.Idle,
        val isPlaceConfirmed: Boolean = true,
        val placeName: String = "",
        val placeCategory: String = "",
        val placeAddress: String = "",
        val latitude: Double = 37.5510324090502,
        val longitude: Double = 126.91228338125131,
        val meetingDate: String = "",
        val meetingTime: String = "0",
        val participants: ImmutableList<CompletedParticipantItemModel> = persistentListOf(
            CompletedParticipantItemModel(id = 1L, name = "", profileImageUrl = "", address = ""),
            CompletedParticipantItemModel(id = 2L, name = "", profileImageUrl = "", address = ""),
            CompletedParticipantItemModel(id = 3L, name = "", profileImageUrl = "", address = ""),
            CompletedParticipantItemModel(id = 4L, name = "", profileImageUrl = "", address = ""),
            CompletedParticipantItemModel(id = 5L, name = "", profileImageUrl = "", address = ""),
        ),
    )
}

sealed interface MeetingResultUiState {
    data object Idle : MeetingResultUiState
    data object Loading : MeetingResultUiState
    data object Success : MeetingResultUiState
    data class Failure(
        val msg: String,
    ) : MeetingResultUiState
}
