package com.moive.app.data.condition.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.condition.mapper.ActivityType
import com.moive.app.data.condition.mapper.toModel
import com.moive.app.data.condition.mapper.toRequest
import com.moive.app.data.condition.model.AvailableScheduleModel
import com.moive.app.data.condition.model.ConditionModel
import com.moive.app.data.condition.remote.datasource.ConditionRemoteDataSource
import com.moive.app.data.condition.remote.dto.ConditionRequest
import javax.inject.Inject

class ConditionRepositoryImpl @Inject constructor(
    private val conditionRemoteDataSource: ConditionRemoteDataSource,
) : ConditionRepository {

    override suspend fun postConditionInput(
        meetingId: Long,
        availableSchedules: List<AvailableScheduleModel>,
        departureName: String,
        departureLatitude: Double,
        departureLongitude: Double,
        maxTravelMinutes: Int?,
        activityTypes: List<ActivityType>,
    ): Result<ConditionModel> =
        suspendRunCatching {
            conditionRemoteDataSource.postConditionInput(
                meetingId = meetingId,
                request = ConditionRequest(
                    availableSchedules = availableSchedules.map { it.toRequest() },
                    departureName = departureName,
                    departureLatitude = departureLatitude,
                    departureLongitude = departureLongitude,
                    maxTravelMinutes = maxTravelMinutes,
                    activityTypes = activityTypes,
                ),
            ).checkData().toModel()
        }
}
