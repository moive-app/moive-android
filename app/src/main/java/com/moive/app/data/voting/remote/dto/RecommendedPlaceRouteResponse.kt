package com.moive.app.data.voting.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedPlaceRouteResponse(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("userLocation")
    val userLocation: LatLngResponse,
    @SerialName("placeLocation")
    val placeLocation: LatLngResponse,
    @SerialName("totalTime")
    val totalTime: Int,
    @SerialName("walkTime")
    val walkTime: Int,
    @SerialName("busTime")
    val busTime: Int,
    @SerialName("subwayTime")
    val subwayTime: Int,
    @SerialName("fare")
    val fare: Int,
    @SerialName("landingUrl")
    val landingUrl: String? = null,
    @SerialName("pathPoints")
    val pathPoints: List<LatLngResponse> = emptyList(),
)

@Serializable
data class LatLngResponse(
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("latitude")
    val latitude: Double,
)
