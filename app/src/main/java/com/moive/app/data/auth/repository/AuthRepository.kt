package com.moive.app.data.auth.repository

import com.moive.app.data.auth.model.KakaoLoginModel

interface AuthRepository {
    suspend fun postKakaoLogin(accessToken: String): Result<KakaoLoginModel>
}
