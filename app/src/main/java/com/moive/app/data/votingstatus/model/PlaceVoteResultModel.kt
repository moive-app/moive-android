package com.moive.app.data.votingstatus.model

data class PlaceVoteResultModel(
    val isFinished: Boolean,
    val totalVoterCount: Int,
    val candidates: List<PlaceVoteCandidateModel>,
)

data class PlaceVoteCandidateModel(
    val id: Long,
    val areaId: Long,
    val placeName: String?,
    val voterCount: Int,
    val isVotedByMe: Boolean,
)
