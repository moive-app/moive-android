package com.moive.app.presentation.meeting.detail

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.mapper.MeetingStatus
import com.moive.app.data.meeting.model.ParticipantItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingDetailContract {
    @Immutable
    data class State(
        val meetingDetailUiState: MeetingDetailUiState = MeetingDetailUiState.Idle,
        val status: MeetingStatus = MeetingStatus.CONDITION_INPUT,
        val meetingName: String = "",
        val meetingPurpose: String = "",
        val thumbnailUrl: String = "",
        val inviteCode: String = "",
        val inviteUrl: String = "",
        val isLeaveMeetingDialogVisible: Boolean = false,
        val participants: ImmutableList<ParticipantItemModel> = persistentListOf(),
        val toolTipMessage: String = "",
        val primaryActionLabel: String = "",
        val primaryActionEnabled: Boolean = false,
    )

    sealed class SideEffect {
        data object NavigateBack : SideEffect()
    }
}

fun statusActionButtonText(status: MeetingStatus, isDone: Boolean): String = when (status) {
    MeetingStatus.CONDITION_INPUT -> if (isDone) "조건 확정" else "조건 입력하기"
    MeetingStatus.VOTING -> "조건 확정"
    MeetingStatus.CONFIRMED -> "모임 확정"
    MeetingStatus.COMPLETED -> "모임 확정"
}

sealed interface MeetingDetailUiState {
    data object Idle : MeetingDetailUiState
    data object Loading : MeetingDetailUiState
    data object Success : MeetingDetailUiState
    data class Failure(
        val msg: String,
    ) : MeetingDetailUiState
}
