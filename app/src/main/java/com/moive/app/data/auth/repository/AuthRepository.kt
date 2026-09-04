package com.moive.app.data.auth.repository

import com.moive.app.data.auth.model.KakaoLoginModel
import com.moive.app.data.auth.model.ReissueModel

interface AuthRepository {
    suspend fun postKakaoLogin(accessToken: String): Result<KakaoLoginModel>

    suspend fun postSignUp(
        accessToken: String,
        isServiceAgreed: Boolean,
        isPrivacyAgreed: Boolean,
        isMarketingAgreed: Boolean,
    ): Result<Unit>

    suspend fun postLogout(): Result<Unit>

    suspend fun postReissue(): Result<ReissueModel>
}
