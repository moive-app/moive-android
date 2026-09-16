package com.moive.app.data.votingstatus.model

import androidx.compose.runtime.Immutable

@Immutable
data class ScheduleVoteResultModel(
    val isVoteSkipped: Boolean,
    val totalVoterCount: Int?,
    val candidates: List<ScheduleVoteCandidateModel>,
)

@Immutable
data class ScheduleVoteCandidateModel(
    val meetingDate: String,
    val meetingTime: String,
    val voterCount: Int?,
    val isVotedByMe: Boolean,
)
