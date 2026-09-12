package com.moive.app.data.voting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceVoteRequest(
    @SerialName("recommendedPlaceIds")
    val recommendedPlaceIds: List<Long>,
)
