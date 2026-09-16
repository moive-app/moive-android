package com.moive.app.data.meeting.mapper

import com.moive.app.data.meeting.model.MeetingCreationModel
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse
import kotlinx.serialization.Serializable

fun MeetingCreationResponse.toModel(): MeetingCreationModel =
    MeetingCreationModel(
        meetingId = meetingId,
        name = name,
        hasSchedule = hasSchedule,
        scheduledDate = scheduledDate,
        scheduledTime = scheduledTime,
        purposeType = purposeType,
        status = status,
        inviteCode = inviteCode,
        inviteUrl = inviteUrl,
        createdAt = createdAt,
    )

@Serializable
enum class MeetingPurposeType(val label: String) {
    FRIENDLY("친목·만남"),
    CELEBRATION("기념·축하"),
    NETWORKING("네트워킹·교류"),
    STUDY("스터디·학습"),
    HOBBY("취미·여가"),
    ETC("기타"),
}
