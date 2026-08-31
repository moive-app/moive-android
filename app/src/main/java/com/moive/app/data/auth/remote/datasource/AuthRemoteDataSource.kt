package com.moive.app.data.auth.remote.datasource

import com.moive.app.data.auth.remote.dto.KakaoLoginResponse
import com.moive.app.data.auth.remote.dto.SignUpResponse
import com.moive.app.data.common.dto.BaseResponse

interface AuthRemoteDataSource {
    suspend fun postKakaoLogin(accessToken: String): BaseResponse<KakaoLoginResponse>

    suspend fun postSignUp(
        accessToken: String,
        isServiceAgreed: Boolean,
        isPrivacyAgreed: Boolean,
        isMarketingAgreed: Boolean,
    ): BaseResponse<SignUpResponse>
}
