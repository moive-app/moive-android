package com.moive.app.data.votingstatus.model

data class PlaceVoteCandidateModel(
    val id: Long,
    val placeName: String,
    val voterCount: Int,
    val isVotedByMe: Boolean,
)
