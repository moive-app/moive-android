package com.moive.app.data.auth.model

import androidx.compose.runtime.Immutable

@Immutable
data class ReissueModel(
    val accessToken: String,
    val refreshToken: String,
)
