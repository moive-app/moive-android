package com.moive.app.data.voting.model

data class PlaceRecommendedPlaceCardItemModel(
    val id: Long,
    val name: String,
    val category: String,
    val matchRate: Int,
    val avgTravelMinutes: Int,
    val maxTravelMinutes: Int,
    val tasteMatchCount: Int,
)
