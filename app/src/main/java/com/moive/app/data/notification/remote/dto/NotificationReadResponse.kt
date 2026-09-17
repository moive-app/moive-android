package com.moive.app.data.notification.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationReadResponse(
    @SerialName("notificationId")
    val notificationId: Long,
    @SerialName("isRead")
    val isRead: Boolean,
    @SerialName("hasUnreadRemaining")
    val hasUnreadRemaining: Boolean,
)
