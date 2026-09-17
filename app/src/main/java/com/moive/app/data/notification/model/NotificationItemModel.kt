package com.moive.app.data.notification.model

import androidx.compose.runtime.Immutable

@Immutable
data class NotificationItemModel(
    val id: Long,
    val type: String,
    val meetingId: Long?,
    val title: String,
    val description: String,
    val time: String,
    val isRead: Boolean,
)
