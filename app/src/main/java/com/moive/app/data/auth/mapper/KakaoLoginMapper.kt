package com.moive.app.data.auth.mapper

import com.moive.app.data.auth.model.KakaoLoginModel
import com.moive.app.data.auth.remote.dto.KakaoLoginResponse

fun KakaoLoginResponse.toModel(): KakaoLoginModel =
    KakaoLoginModel(
        registered = registered,
        nickname = nickname,
        profileImageUrl = profileImageUrl,
        email = email,
    )
