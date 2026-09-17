package com.moive.app.data.notification.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.notification.mapper.toModel
import com.moive.app.data.notification.model.NotificationListModel
import com.moive.app.data.notification.remote.datasource.NotificationRemoteDataSource
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationRemoteDataSource: NotificationRemoteDataSource,
) : NotificationRepository {

    override suspend fun getNotificationList(cursor: Long?, size: Int): Result<NotificationListModel> =
        suspendRunCatching {
            notificationRemoteDataSource.getNotificationList(cursor, size).checkData().toModel()
        }
}
