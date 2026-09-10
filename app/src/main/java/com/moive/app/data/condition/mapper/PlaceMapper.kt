package com.moive.app.data.condition.mapper

import com.moive.app.data.condition.model.PlaceSearchItemModel
import com.moive.app.data.condition.remote.dto.KakaoPlaceDocument

fun KakaoPlaceDocument.toModel(): PlaceSearchItemModel =
    PlaceSearchItemModel(
        id = id.toLong(),
        name = placeName,
        address = roadAddressName.ifBlank { addressName },
        latitude = latitude.toDoubleOrNull() ?: 0.0,
        longitude = longitude.toDoubleOrNull() ?: 0.0,
    )
