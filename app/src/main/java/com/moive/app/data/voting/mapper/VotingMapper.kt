package com.moive.app.data.voting.mapper

import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.data.voting.model.PlaceRecommendedPlaceCardItemModel
import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.data.voting.remote.dto.LatLngResponse
import com.moive.app.data.voting.remote.dto.RecommendedAreaResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceDetailResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceItem
import com.moive.app.data.voting.remote.dto.RecommendedPlaceRouteResponse

fun RecommendedAreaResponse.toModel(): RegionPinModel =
    RegionPinModel(
        id = recommendedAreaId,
        name = name,
        locationX = longitude,
        locationY = latitude,
    )

fun RecommendedPlaceItem.toModel(): PlaceRecommendedPlaceCardItemModel =
    PlaceRecommendedPlaceCardItemModel(
        id = recommendedPlaceId,
        name = name,
        category = category,
        matchRate = preferenceMatchRate,
        avgTravelMinutes = averageTravelTime,
        maxTravelMinutes = maxTravelTime,
        tasteMatchCount = preferenceMatchCnt,
    )

fun RecommendedPlaceDetailResponse.applyTo(place: PlaceDetailModel): PlaceDetailModel =
    place.copy(
        id = recommendedPlaceId,
        placeName = name,
        category = category,
        address = address,
        areaName = areaName,
        matchMemberCount = preferenceMatchCnt,
        avgTravelMinutes = averageTravelTime,
        imageList = imageUrls,
    )

fun LatLngResponse.toModel(): PlaceDetailPinLatLang =
    PlaceDetailPinLatLang(
        latitude = latitude,
        longitude = longitude,
    )

fun RecommendedPlaceRouteResponse.applyTo(place: PlaceDetailModel): PlaceDetailModel =
    place.copy(
        userName = nickname,
        startPinLatLang = userLocation.toModel(),
        endPinLatLang = placeLocation.toModel(),
        routeLatLang = PlaceDetailRouteLatLang(
            latitude = pathPoints.map { it.latitude },
            longitude = pathPoints.map { it.longitude },
        ),
        totalTravelMinutes = totalTime,
        walkMinutes = walkTime,
        busMinutes = busTime,
        subwayMinutes = subwayTime,
        travelFare = fare,
        landingUrl = landingUrl ?: "",
    )
