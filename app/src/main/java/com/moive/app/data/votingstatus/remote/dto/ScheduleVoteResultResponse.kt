package com.moive.app.data.votingstatus.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleVoteResultResponse(
    @SerialName("isVoteSkipped")
    val isVoteSkipped: Boolean,
    @SerialName("totalVoterCnt")
    val totalVoterCnt: Int?,
    @SerialName("candidates")
    val candidates: List<ScheduleVoteCandidateItem>,
)

@Serializable
data class ScheduleVoteCandidateItem(
    @SerialName("meetingDate")
    val meetingDate: String,
    @SerialName("meetingTime")
    val meetingTime: String,
    @SerialName("voterCnt")
    val voterCnt: Int?,
    @SerialName("isVotedByMe")
    val isVotedByMe: Boolean,
)
