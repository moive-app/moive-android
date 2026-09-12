package com.moive.app.data.condition.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.condition.remote.dto.ConditionRequest
import com.moive.app.data.condition.remote.dto.ConditionResponse
import com.moive.app.data.condition.remote.service.ConditionService
import javax.inject.Inject

class ConditionRemoteDataSourceImpl @Inject constructor(
    private val conditionService: ConditionService,
) : ConditionRemoteDataSource {

    override suspend fun postConditionInput(
        meetingId: Long,
        request: ConditionRequest,
    ): BaseResponse<ConditionResponse> =
        conditionService.postConditionInput(meetingId, request)
}
