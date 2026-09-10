package com.moive.app.data.meeting.model

data class TravelParticipantModel(
    val id: Long,
    val name: String,
    val profileImageUrl: String,
    val address: String,
    val transferCount: Int,
    val travelMinutes: Int,
)
