package com.moive.app.data.meeting.mapper

import com.moive.app.data.meeting.model.MeetingDetailModel
import com.moive.app.data.meeting.model.ParticipantItemModel
import com.moive.app.data.meeting.remote.dto.MeetingDetailResponse
import com.moive.app.data.meeting.remote.dto.ParticipantResponse
import kotlinx.collections.immutable.toImmutableList

fun MeetingDetailResponse.toModel(): MeetingDetailModel =
    MeetingDetailModel(
        meetingId = meetingId,
        name = name,
        purposeType = MeetingPurposeType.entries.find { it.name == purposeType } ?: MeetingPurposeType.ETC,
        status = status.toMeetingStatus(),
        hasSchedule = hasSchedule,
        scheduledDate = scheduledDate,
        scheduledTime = scheduledTime,
        inviteCode = inviteCode,
        inviteUrl = inviteUrl,
        participants = participants.map { it.toModel() }.toImmutableList(),
        homeMessage = homeMessage,
        primaryActionLabel = primaryActionLabel,
        primaryActionEnabled = primaryActionEnabled,
    )

fun ParticipantResponse.toModel(): ParticipantItemModel =
    ParticipantItemModel(
        id = participantId,
        name = nickname,
        profileImageUrl = profileImageUrl,
        statusLabel = participantStateLabel,
        isMe = isMe,
        isDone = participantState.endsWith("_DONE"),
    )
