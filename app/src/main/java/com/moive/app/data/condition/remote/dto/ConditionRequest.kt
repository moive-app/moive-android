package com.moive.app.data.condition.remote.dto

import com.moive.app.data.condition.mapper.ActivityType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionRequest(
    @SerialName("availableSchedules")
    val availableSchedules: List<AvailableScheduleRequest>,
    @SerialName("departureName")
    val departureName: String,
    @SerialName("departureLatitude")
    val departureLatitude: Double,
    @SerialName("departureLongitude")
    val departureLongitude: Double,
    @SerialName("maxTravelMinutes")
    val maxTravelMinutes: Int?,
    @SerialName("activityTypes")
    val activityTypes: List<ActivityType>,
)

@Serializable
data class AvailableScheduleRequest(
    @SerialName("date")
    val date: String,
    @SerialName("time")
    val time: String,
)
