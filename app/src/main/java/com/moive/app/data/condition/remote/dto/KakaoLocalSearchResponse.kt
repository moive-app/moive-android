package com.moive.app.data.condition.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoLocalSearchResponse(
    @SerialName("documents")
    val documents: List<KakaoPlaceDocument>,
)

@Serializable
data class KakaoPlaceDocument(
    @SerialName("id")
    val id: String,
    @SerialName("place_name")
    val placeName: String,
    @SerialName("address_name")
    val addressName: String,
    @SerialName("road_address_name")
    val roadAddressName: String,
    @SerialName("x")
    val longitude: String,
    @SerialName("y")
    val latitude: String,
)
