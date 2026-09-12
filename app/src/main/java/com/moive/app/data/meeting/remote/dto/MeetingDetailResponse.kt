package com.moive.app.data.meeting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingDetailResponse(
    @SerialName("meetingId")
    val meetingId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("purposeType")
    val purposeType: String,
    @SerialName("status")
    val status: String,
    @SerialName("hasSchedule")
    val hasSchedule: Boolean,
    @SerialName("scheduledDate")
    val scheduledDate: String?,
    @SerialName("scheduledTime")
    val scheduledTime: String?,
    @SerialName("inviteCode")
    val inviteCode: String,
    @SerialName("inviteUrl")
    val inviteUrl: String,
    @SerialName("participants")
    val participants: List<ParticipantResponse>,
    @SerialName("homeMessage")
    val homeMessage: String,
    @SerialName("primaryActionLabel")
    val primaryActionLabel: String,
    @SerialName("primaryActionEnabled")
    val primaryActionEnabled: Boolean,
)

@Serializable
data class ParticipantResponse(
    @SerialName("participantId")
    val participantId: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String?,
    @SerialName("participantState")
    val participantState: String,
    @SerialName("participantStateLabel")
    val participantStateLabel: String,
    @SerialName("isMe")
    val isMe: Boolean,
)
