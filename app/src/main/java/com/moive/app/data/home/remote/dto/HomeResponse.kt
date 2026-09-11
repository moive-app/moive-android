package com.moive.app.data.home.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("confirmedMeetings")
    val confirmedMeetings: List<ConfirmedMeetingResponse>,
    @SerialName("myMeetings")
    val myMeetings: List<MyMeetingResponse>,
)

@Serializable
data class ConfirmedMeetingResponse(
    @SerialName("meetingId")
    val meetingId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("confirmedPlaceName")
    val confirmedPlaceName: String,
    @SerialName("confirmedDate")
    val confirmedDate: String,
    @SerialName("confirmedTime")
    val confirmedTime: String,
    @SerialName("participantProfileImages")
    val participantProfileImages: List<String>,
    @SerialName("participantCnt")
    val participantCnt: Int,
    @SerialName("dDay")
    val dDay: Int,
)

@Serializable
data class MyMeetingResponse(
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
)
