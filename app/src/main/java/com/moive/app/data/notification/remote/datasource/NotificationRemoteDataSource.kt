package com.moive.app.data.notification.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.notification.remote.dto.DeviceTokenRequest
import com.moive.app.data.notification.remote.dto.NotificationListResponse
import com.moive.app.data.notification.remote.dto.NotificationReadResponse
import com.moive.app.data.notification.remote.dto.NotificationUnreadStatusResponse

interface NotificationRemoteDataSource {
    suspend fun getNotificationList(cursor: Long?, size: Int): BaseResponse<NotificationListResponse>

    suspend fun getUnreadStatus(): BaseResponse<NotificationUnreadStatusResponse>

    suspend fun patchNotificationRead(notificationId: Long): BaseResponse<NotificationReadResponse>

    suspend fun putDeviceToken(request: DeviceTokenRequest): BaseResponse<Unit>
}
