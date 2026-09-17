package com.moive.app.data.notification.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.common.dto.checkSuccess
import com.moive.app.data.notification.mapper.toModel
import com.moive.app.data.notification.model.NotificationListModel
import com.moive.app.data.notification.remote.datasource.NotificationRemoteDataSource
import com.moive.app.data.notification.remote.dto.DeviceTokenRequest
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationRemoteDataSource: NotificationRemoteDataSource,
) : NotificationRepository {

    override suspend fun getNotificationList(cursor: Long?, size: Int): Result<NotificationListModel> =
        suspendRunCatching {
            notificationRemoteDataSource.getNotificationList(cursor, size).checkData().toModel()
        }

    override suspend fun getUnreadStatus(): Result<Boolean> =
        suspendRunCatching {
            notificationRemoteDataSource.getUnreadStatus().checkData().toModel()
        }

    override suspend fun patchNotificationRead(notificationId: Long): Result<Unit> =
        suspendRunCatching {
            notificationRemoteDataSource.patchNotificationRead(notificationId).checkSuccess()
        }

    override suspend fun putDeviceToken(fcmToken: String, deviceId: String): Result<Unit> =
        suspendRunCatching {
            notificationRemoteDataSource.putDeviceToken(
                DeviceTokenRequest(fcmToken = fcmToken, deviceId = deviceId),
            ).checkSuccess()
        }

    override suspend fun deleteDeviceToken(deviceId: String): Result<Unit> =
        suspendRunCatching {
            notificationRemoteDataSource.deleteDeviceToken(deviceId).checkSuccess()
        }
}
