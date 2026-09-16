package com.moive.app.data.condition.model

import androidx.compose.runtime.Immutable

@Immutable
data class ConditionModel(
    val meetingId: Long,
    val participantId: Long,
    val participantState: String,
    val participantStateLabel: String,
    val meetingStatus: String,
    val recommendationTriggered: Boolean,
)
