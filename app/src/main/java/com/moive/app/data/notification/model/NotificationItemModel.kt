package com.moive.app.data.notification.model

data class NotificationItemModel(
    val id: Long,
    val title: String,
    val description: String,
    val time: String,
    val isRead: Boolean,
)
