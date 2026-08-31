package com.moive.app.data.auth.model

import androidx.compose.runtime.Immutable

@Immutable
data class KakaoLoginModel(
    val registered: Boolean,
    val nickname: String,
    val profileImageUrl: String,
    val email: String,
)
