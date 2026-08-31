package com.moive.app.data.auth.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoLoginResponse(
    @SerialName("registered")
    val registered: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("email")
    val email: String,
    @SerialName("token")
    val token: AuthToken?,
)

@Serializable
data class AuthToken(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
)
