package com.moive.app.data.meeting.model

import androidx.compose.runtime.Immutable
import com.moive.app.data.meeting.mapper.MeetingPurposeType
import com.moive.app.data.meeting.mapper.MeetingStatus
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class MeetingDetailModel(
    val meetingId: Long,
    val name: String,
    val purposeType: MeetingPurposeType,
    val status: MeetingStatus,
    val inviteCode: String,
    val inviteUrl: String,
    val participants: ImmutableList<ParticipantItemModel>,
    val homeMessage: String,
    val primaryActionLabel: String,
    val primaryActionEnabled: Boolean,
)

@Immutable
data class ParticipantItemModel(
    val id: Long,
    val name: String,
    val profileImageUrl: String?,
    val statusLabel: String,
    val isMe: Boolean,
    val isDone: Boolean,
)
