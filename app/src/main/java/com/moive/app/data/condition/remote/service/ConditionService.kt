package com.moive.app.data.condition.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.condition.remote.dto.ConditionRequest
import com.moive.app.data.condition.remote.dto.ConditionResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ConditionService {

    @POST("meetings/{meetingId}/preferences")
    suspend fun postConditionInput(
        @Path("meetingId") meetingId: Long,
        @Body request: ConditionRequest,
    ): BaseResponse<ConditionResponse>
}
