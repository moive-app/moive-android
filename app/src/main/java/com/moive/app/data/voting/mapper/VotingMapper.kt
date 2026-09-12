package com.moive.app.data.voting.mapper

import com.moive.app.data.voting.model.PlaceRecommendedPlaceCardItemModel
import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.data.voting.remote.dto.RecommendedAreaResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceItem

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
