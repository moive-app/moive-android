package com.moive.app.data.meeting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingResultResponse(
    @SerialName("status")
    val status: String,
    @SerialName("place")
    val place: MeetingResultPlaceResponse?,
    @SerialName("meetingDate")
    val meetingDate: String,
    @SerialName("meetingTime")
    val meetingTime: String,
    @SerialName("participants")
    val participants: List<MeetingResultParticipantResponse>,
)

@Serializable
data class MeetingResultPlaceResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("areaId")
    val areaId: Long,
    @SerialName("isFetchFailed")
    val isFetchFailed: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("category")
    val category: String,
    @SerialName("location")
    val location: MeetingResultLocationResponse,
)

@Serializable
data class MeetingResultLocationResponse(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
)

@Serializable
data class MeetingResultParticipantResponse(
    @SerialName("profileImageUrl")
    val profileImageUrl: String?,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("address")
    val address: String?,
    @SerialName("transferCnt")
    val transferCnt: Int?,
    @SerialName("totalTime")
    val totalTime: Int?,
)
