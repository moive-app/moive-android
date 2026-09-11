package com.moive.app.data.meeting.model

import com.moive.app.data.meeting.mapper.MeetingPurposeType

data class MeetingCreationModel(
    val meetingId: Long,
    val name: String,
    val hasSchedule: Boolean,
    val scheduledDate: String?,
    val scheduledTime: String?,
    val purposeType: MeetingPurposeType,
    val status: String,
    val inviteCode: String,
    val inviteUrl: String,
    val createdAt: String,
)
