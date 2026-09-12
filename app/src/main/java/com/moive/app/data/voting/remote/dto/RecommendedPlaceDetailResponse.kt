package com.moive.app.data.voting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedPlaceDetailResponse(
    @SerialName("recommendedPlaceId")
    val recommendedPlaceId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("category")
    val category: String,
    @SerialName("address")
    val address: String,
    @SerialName("areaName")
    val areaName: String,
    @SerialName("preferenceMatchCnt")
    val preferenceMatchCnt: Int,
    @SerialName("averageTravelTime")
    val averageTravelTime: Int,
    @SerialName("imageUrls")
    val imageUrls: List<String> = emptyList(),
)
