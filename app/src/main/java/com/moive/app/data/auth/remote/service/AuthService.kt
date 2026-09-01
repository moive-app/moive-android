package com.moive.app.data.auth.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.auth.remote.dto.SignUpResponse
import com.moive.app.data.auth.remote.dto.KakaoLoginRequest
import com.moive.app.data.auth.remote.dto.KakaoLoginResponse
import com.moive.app.data.auth.remote.dto.LogoutRequest
import com.moive.app.data.auth.remote.dto.ReissueRequest
import com.moive.app.data.auth.remote.dto.ReissueResponse
import com.moive.app.data.auth.remote.dto.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/kakao")
    suspend fun postKakaoLogin(
        @Body request: KakaoLoginRequest,
    ): BaseResponse<KakaoLoginResponse>

    @POST("auth/signup")
    suspend fun postSignUp(
        @Body request: SignUpRequest,
    ): BaseResponse<SignUpResponse>

    @POST("auth/logout")
    suspend fun postLogout(
        @Body request: LogoutRequest,
    ): BaseResponse<Unit>

    @POST("auth/reissue")
    suspend fun postReissue(
        @Body request: ReissueRequest,
    ): BaseResponse<ReissueResponse>
}
