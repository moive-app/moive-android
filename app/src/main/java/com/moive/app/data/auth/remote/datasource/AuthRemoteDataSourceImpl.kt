package com.moive.app.data.auth.remote.datasource

import com.moive.app.data.auth.remote.dto.KakaoLoginRequest
import com.moive.app.data.auth.remote.dto.KakaoLoginResponse
import com.moive.app.data.auth.remote.service.AuthService
import com.moive.app.data.common.dto.BaseResponse
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRemoteDataSource {

    override suspend fun postKakaoLogin(accessToken: String): BaseResponse<KakaoLoginResponse> =
        authService.postKakaoLogin(KakaoLoginRequest(accessToken))
}
