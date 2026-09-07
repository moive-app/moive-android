package com.moive.app.presentation.meeting.detail

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.model.ParticipantItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingDetailContract {
    @Immutable
    data class State(
        val status: MeetingStatus = MeetingStatus.INPUTTING,
        val meetingName: String = "모임명",
        val meetingPurpose: String = "모임목적",
        val thumbnailUrl: String = "",
        val isLeaveMeetingDialogVisible: Boolean = false,
        val participants: ImmutableList<ParticipantItemModel> = persistentListOf(
            ParticipantItemModel(
                id = 1L,
                name = "사용자",
                profileImageUrl = "",
                isMe = true,
                isDone = false
            ),
            ParticipantItemModel(
                id = 2L,
                name = "참여자1",
                profileImageUrl = "",
                isMe = false,
                isDone = true
            ),
            ParticipantItemModel(
                id = 3L,
                name = "참여자2",
                profileImageUrl = "",
                isMe = false,
                isDone = false
            ),
        ),
    ) {
        val isAllParticipantsDone: Boolean
            get() = participants.all { it.isDone }
    }

    enum class MeetingStatus {
        INPUTTING,
        VOTING,
        CONFIRMED;
    }
}
