package com.moive.app.data.condition.repository

import com.moive.app.data.condition.mapper.ActivityType
import com.moive.app.data.condition.model.AvailableScheduleModel
import com.moive.app.data.condition.model.ConditionModel

interface ConditionRepository {
    suspend fun postConditionInput(
        meetingId: Long,
        availableSchedules: List<AvailableScheduleModel>,
        departureName: String,
        departureLatitude: Double,
        departureLongitude: Double,
        maxTravelMinutes: Int?,
        activityTypes: List<ActivityType>,
    ): Result<ConditionModel>
}
