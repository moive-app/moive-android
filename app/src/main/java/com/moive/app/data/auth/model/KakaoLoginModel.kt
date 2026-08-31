package com.moive.app.data.auth.model

data class KakaoLoginModel(
    val registered: Boolean,
    val nickname: String,
    val profileImageUrl: String,
    val email: String,
)
