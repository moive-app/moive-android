package com.moive.app.data.notification.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.notification.remote.dto.NotificationListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationService {

    @GET("notifications")
    suspend fun getNotificationList(
        @Query("cursor") cursor: Long?,
        @Query("size") size: Int,
    ): BaseResponse<NotificationListResponse>
}
