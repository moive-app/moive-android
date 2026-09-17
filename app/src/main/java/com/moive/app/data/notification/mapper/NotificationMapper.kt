package com.moive.app.data.notification.mapper

import com.moive.app.core.extensions.toRelativeTime
import com.moive.app.data.notification.model.NotificationItemModel
import com.moive.app.data.notification.model.NotificationListModel
import com.moive.app.data.notification.remote.dto.NotificationItemResponse
import com.moive.app.data.notification.remote.dto.NotificationListResponse
import kotlinx.collections.immutable.toImmutableList

fun NotificationListResponse.toModel(): NotificationListModel =
    NotificationListModel(
        notifications = notifications.map { it.toModel() }.toImmutableList(),
        hasNext = hasNext,
        nextCursor = nextCursor,
    )

fun NotificationItemResponse.toModel(): NotificationItemModel =
    NotificationItemModel(
        id = notificationId,
        type = type,
        meetingId = meetingId,
        title = title,
        description = content,
        time = createdAt.toRelativeTime(),
        isRead = isRead,
    )
