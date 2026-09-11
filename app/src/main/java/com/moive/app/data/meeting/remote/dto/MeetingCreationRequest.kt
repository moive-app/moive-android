package com.moive.app.data.meeting.remote.dto

import com.moive.app.data.meeting.mapper.MeetingPurposeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingCreationRequest(
    @SerialName("name")
    val name: String,
    @SerialName("hasSchedule")
    val hasSchedule: Boolean,
    @SerialName("scheduledDate")
    val scheduledDate: String?,
    @SerialName("scheduledTime")
    val scheduledTime: String?,
    @SerialName("purposeType")
    val purposeType: MeetingPurposeType,
)
