package com.moive.app.data.auth.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.auth.remote.dto.KakaoLoginRequest
import com.moive.app.data.auth.remote.dto.KakaoLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/kakao")
    suspend fun postKakaoLogin(
        @Body request: KakaoLoginRequest,
    ): BaseResponse<KakaoLoginResponse>
}
