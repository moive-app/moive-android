package com.moive.app.data.meeting.model

import androidx.compose.runtime.Immutable

@Immutable
data class MeetingResultModel(
    val place: MeetingResultPlaceModel?,
    val meetingDate: String,
    val meetingTime: String,
    val participants: List<MeetingResultParticipantModel>,
)

@Immutable
data class MeetingResultPlaceModel(
    val id: Long,
    val name: String,
    val address: String,
    val category: String,
    val latitude: Double,
    val longitude: Double,
)

@Immutable
data class MeetingResultParticipantModel(
    val id: Long,
    val name: String,
    val profileImageUrl: String,
    val address: String,
    val transferCount: Int,
    val travelMinutes: Int,
)
