package com.moive.app.data.votingstatus.model

data class ScheduleVoteCandidateModel(
    val meetingDate: String,
    val meetingTime: String,
    val voterCount: Int?,
    val isVotedByMe: Boolean,
)
