package com.moive.app.data.auth.remote.datasource

import com.moive.app.data.auth.remote.dto.AgreementItem
import com.moive.app.data.auth.remote.dto.AgreementType
import com.moive.app.data.auth.remote.dto.KakaoLoginRequest
import com.moive.app.data.auth.remote.dto.KakaoLoginResponse
import com.moive.app.data.auth.remote.dto.LogoutRequest
import com.moive.app.data.auth.remote.dto.SignUpRequest
import com.moive.app.data.auth.remote.dto.SignUpResponse
import com.moive.app.data.auth.remote.service.AuthService
import com.moive.app.data.common.dto.BaseResponse
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRemoteDataSource {

    override suspend fun postKakaoLogin(accessToken: String): BaseResponse<KakaoLoginResponse> =
        authService.postKakaoLogin(KakaoLoginRequest(accessToken))

    override suspend fun postSignUp(
        accessToken: String,
        isServiceAgreed: Boolean,
        isPrivacyAgreed: Boolean,
        isMarketingAgreed: Boolean,
    ): BaseResponse<SignUpResponse> {
        val agreements = listOf(
            AgreementItem(AgreementType.SERVICE, AGREEMENT_VERSION, isServiceAgreed),
            AgreementItem(AgreementType.PRIVACY, AGREEMENT_VERSION, isPrivacyAgreed),
            AgreementItem(AgreementType.MARKETING, AGREEMENT_VERSION, isMarketingAgreed),
        )

        return authService.postSignUp(SignUpRequest(accessToken, agreements))
    }

    override suspend fun postLogout(refreshToken: String): BaseResponse<Unit> =
        authService.postLogout(LogoutRequest(refreshToken))

    companion object {
        private const val AGREEMENT_VERSION = "1.0"
    }
}
