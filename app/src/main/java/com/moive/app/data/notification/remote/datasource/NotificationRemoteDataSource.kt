package com.moive.app.data.notification.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.notification.remote.dto.NotificationListResponse

interface NotificationRemoteDataSource {
    suspend fun getNotificationList(cursor: Long?, size: Int): BaseResponse<NotificationListResponse>
}
