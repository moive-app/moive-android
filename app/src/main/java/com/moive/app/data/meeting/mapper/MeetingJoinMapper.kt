package com.moive.app.data.meeting.mapper

import com.moive.app.data.meeting.model.MeetingJoinModel
import com.moive.app.data.meeting.remote.dto.MeetingJoinResponse

fun MeetingJoinResponse.toModel(): MeetingJoinModel =
    MeetingJoinModel(
        meetingId = meetingId,
        participantId = participantId,
        participantStateLabel = participantStateLabel,
        alreadyParticipant = alreadyParticipant,
    )
