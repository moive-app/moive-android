package com.moive.app.data.meeting.model

import androidx.compose.runtime.Immutable

@Immutable
data class MeetingJoinModel(
    val meetingId: Long,
    val participantId: Long,
    val participantStateLabel: String,
    val alreadyParticipant: Boolean,
)
