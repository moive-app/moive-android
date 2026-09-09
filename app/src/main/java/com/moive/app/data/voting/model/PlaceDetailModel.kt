package com.moive.app.data.voting.model

import androidx.compose.runtime.Immutable

@Immutable
data class PlaceDetailModel(
    val id: Long,
    val region: String,
    val userName: String,
    val placeName: String,
    val category: String,
    val address: String,
    val totalMemberCount: Int,
    val matchMemberCount: Int,
    val imageList: List<PlaceDetailImageItemModel>,
    val startPinLatLang: PlaceDetailPinLatLang,
    val endPinLatLang: PlaceDetailPinLatLang,
    val routeLatLang: PlaceDetailRouteLatLang,
    val totalTravelMinutes: Int,
    val avgTravelMinutes: Int,
    val walkMinutes: Int,
    val busMinutes: Int,
    val subwayMinutes: Int,
    val travelFare: Int,
)

@Immutable
data class PlaceDetailImageItemModel(
    val id: Long,
    val imageUrl: String,
)

@Immutable
data class PlaceDetailRouteLatLang(
    val latitude: List<Double>,
    val longitude: List<Double>,
)

@Immutable
data class PlaceDetailPinLatLang(
    val latitude: Double,
    val longitude: Double,
)


