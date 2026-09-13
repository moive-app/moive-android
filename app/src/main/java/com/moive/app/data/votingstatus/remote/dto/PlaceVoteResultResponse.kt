package com.moive.app.data.votingstatus.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceVoteResultResponse(
    @SerialName("isFinished")
    val isFinished: Boolean,
    @SerialName("totalVoterCnt")
    val totalVoterCnt: Int,
    @SerialName("candidates")
    val candidates: List<PlaceVoteCandidateItem>,
)

@Serializable
data class PlaceVoteCandidateItem(
    @SerialName("placeId")
    val placeId: Long,
    @SerialName("placeAreaId")
    val placeAreaId: Long,
    @SerialName("placeName")
    val placeName: String?,
    @SerialName("voterCnt")
    val voterCnt: Int,
    @SerialName("isVotedByMe")
    val isVotedByMe: Boolean,
)
