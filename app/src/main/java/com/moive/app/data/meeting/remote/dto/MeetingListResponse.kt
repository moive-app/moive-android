package com.moive.app.data.meeting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingListResponse(
    @SerialName("meetings")
    val meetings: List<MeetingItemResponse>,
    @SerialName("hasNext")
    val hasNext: Boolean,
    @SerialName("nextCursor")
    val nextCursor: Long?,
)

@Serializable
data class MeetingItemResponse(
    @SerialName("meetingId")
    val meetingId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("purposeType")
    val purposeType: String,
    @SerialName("status")
    val status: String,
    @SerialName("statusLabel")
    val statusLabel: String,
    @SerialName("scheduledDate")
    val scheduledDate: String?,
    @SerialName("scheduledTime")
    val scheduledTime: String?,
    @SerialName("participantProfileImages")
    val participantProfileImages: List<String> = emptyList(),
    @SerialName("participantCnt")
    val participantCnt: Int,
    @SerialName("submittedCnt")
    val submittedCnt: Int,
)
