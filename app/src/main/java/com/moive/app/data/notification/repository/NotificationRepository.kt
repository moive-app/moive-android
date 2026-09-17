package com.moive.app.data.notification.repository

import com.moive.app.data.notification.model.NotificationListModel

interface NotificationRepository {
    suspend fun getNotificationList(cursor: Long?, size: Int): Result<NotificationListModel>

    suspend fun getUnreadStatus(): Result<Boolean>
}
