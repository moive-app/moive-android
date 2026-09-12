package com.moive.app.data.voting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedAreaListResponse(
    @SerialName("areas")
    val areas: List<RecommendedAreaResponse>,
)

@Serializable
data class RecommendedAreaResponse(
    @SerialName("recommendedAreaId")
    val recommendedAreaId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
)
