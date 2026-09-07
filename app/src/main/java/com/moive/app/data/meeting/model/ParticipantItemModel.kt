package com.moive.app.data.meeting.model

import androidx.compose.runtime.Immutable

@Immutable
data class ParticipantItemModel(
    val id: Long,
    val name: String,
    val profileImageUrl: String,
    val isMe: Boolean,
    val isDone: Boolean,
)
