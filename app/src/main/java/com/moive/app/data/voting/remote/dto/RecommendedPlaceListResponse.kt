package com.moive.app.data.voting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedPlaceListResponse(
    @SerialName("recommendedAreaId")
    val recommendedAreaId: Long,
    @SerialName("places")
    val places: List<RecommendedPlaceItem>,
)

@Serializable
data class RecommendedPlaceItem(
    @SerialName("recommendedPlaceId")
    val recommendedPlaceId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("category")
    val category: String,
    @SerialName("preferenceMatchRate")
    val preferenceMatchRate: Int,
    @SerialName("averageTravelTime")
    val averageTravelTime: Int,
    @SerialName("maxTravelTime")
    val maxTravelTime: Int,
    @SerialName("preferenceMatchCnt")
    val preferenceMatchCnt: Int,
)
