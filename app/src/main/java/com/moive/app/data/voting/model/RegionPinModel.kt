package com.moive.app.data.voting.model

import androidx.compose.runtime.Immutable

@Immutable
data class RegionPinModel(
    val id: Long,
    val name: String,
    val locationX: Double,
    val locationY: Double,
)
