package com.moive.app.data.condition.model

import androidx.compose.runtime.Immutable

@Immutable
data class AvailableScheduleModel(
    val date: String,
    val time: String,
)
