package com.moive.app.data.notification.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.notification.remote.dto.DeviceTokenRequest
import com.moive.app.data.notification.remote.dto.NotificationListResponse
import com.moive.app.data.notification.remote.dto.NotificationReadResponse
import com.moive.app.data.notification.remote.dto.NotificationUnreadStatusResponse
import com.moive.app.data.notification.remote.service.NotificationService
import javax.inject.Inject

class NotificationRemoteDataSourceImpl @Inject constructor(
    private val notificationService: NotificationService,
) : NotificationRemoteDataSource {

    override suspend fun getNotificationList(cursor: Long?, size: Int): BaseResponse<NotificationListResponse> =
        notificationService.getNotificationList(cursor, size)

    override suspend fun getUnreadStatus(): BaseResponse<NotificationUnreadStatusResponse> =
        notificationService.getUnreadStatus()

    override suspend fun patchNotificationRead(notificationId: Long): BaseResponse<NotificationReadResponse> =
        notificationService.patchNotificationRead(notificationId)

    override suspend fun putDeviceToken(request: DeviceTokenRequest): BaseResponse<Unit> =
        notificationService.putDeviceToken(request)
}
