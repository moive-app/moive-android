package com.moive.app.data.voting.mapper

import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.data.voting.remote.dto.RecommendedAreaResponse

fun RecommendedAreaResponse.toModel(): RegionPinModel =
    RegionPinModel(
        id = recommendedAreaId,
        name = name,
        locationX = longitude,
        locationY = latitude,
    )
