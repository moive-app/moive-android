package com.moive.app.data.meeting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingJoinResponse(
    @SerialName("meetingId")
    val meetingId: Long,
    @SerialName("participantId")
    val participantId: Long,
    @SerialName("participantState")
    val participantState: String,
    @SerialName("participantStateLabel")
    val participantStateLabel: String,
    @SerialName("alreadyParticipant")
    val alreadyParticipant: Boolean,
)
