package com.moive.app.data.meeting.remote.dto

import com.moive.app.data.meeting.mapper.MeetingPurposeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingCreationResponse(
    @SerialName("meetingId")
    val meetingId: Long,
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
    @SerialName("status")
    val status: String,
    @SerialName("inviteCode")
    val inviteCode: String,
    @SerialName("inviteUrl")
    val inviteUrl: String,
    @SerialName("createdAt")
    val createdAt: String,
)
