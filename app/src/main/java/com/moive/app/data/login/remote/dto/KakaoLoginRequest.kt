package com.moive.app.data.login.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoLoginRequest(
    @SerialName("accessToken")
    val accessToken: String,
)
