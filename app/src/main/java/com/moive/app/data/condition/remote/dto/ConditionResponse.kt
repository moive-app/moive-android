package com.moive.app.data.condition.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionResponse(
    @SerialName("meetingId")
    val meetingId: Long,
    @SerialName("participantId")
    val participantId: Long,
    @SerialName("participantState")
    val participantState: String,
    @SerialName("participantStateLabel")
    val participantStateLabel: String,
    @SerialName("meetingStatus")
    val meetingStatus: String,
    @SerialName("recommendationTriggered")
    val recommendationTriggered: Boolean,
)
