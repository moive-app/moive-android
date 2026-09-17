package com.moive.app.data.notification.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationUnreadStatusResponse(
    @SerialName("hasUnread")
    val hasUnread: Boolean,
)
