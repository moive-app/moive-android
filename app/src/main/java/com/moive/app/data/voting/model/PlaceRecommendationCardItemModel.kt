package com.moive.app.data.voting.model

data class PlaceRecommendationCardItemModel(
    val id: Long,
    val name: String,
    val category: String,
    val address: String,
    val matchRate: Int,
    val avgTravelMinutes: Int,
    val maxTravelMinutes: Int,
    val tasteMatchCount: Int,
    val tasteMatchTotal: Int,
    val totalTravelMinutes: Int,
    val totalTravelFare: Int,
)
