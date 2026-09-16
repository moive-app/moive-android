package com.moive.app.data.condition.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.condition.remote.dto.ConditionRequest
import com.moive.app.data.condition.remote.dto.ConditionResponse

interface ConditionRemoteDataSource {
    suspend fun postConditionInput(
        meetingId: Long,
        request: ConditionRequest,
    ): BaseResponse<ConditionResponse>
}
