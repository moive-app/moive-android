package com.moive.app.data.notification.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationListResponse(
    @SerialName("notifications")
    val notifications: List<NotificationItemResponse>,
    @SerialName("hasNext")
    val hasNext: Boolean,
    @SerialName("nextCursor")
    val nextCursor: Long?,
)

@Serializable
data class NotificationItemResponse(
    @SerialName("notificationId")
    val notificationId: Long,
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("meetingId")
    val meetingId: Long?,
    @SerialName("isRead")
    val isRead: Boolean,
    @SerialName("createdAt")
    val createdAt: String,
)
