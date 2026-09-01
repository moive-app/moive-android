package com.moive.app.data.auth.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.auth.mapper.toModel
import com.moive.app.data.auth.model.KakaoLoginModel
import com.moive.app.data.auth.remote.datasource.AuthRemoteDataSource
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.common.dto.checkSuccess
import com.moive.app.data.local.token.LocalTokenDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val localTokenDatasource: LocalTokenDataSource,
) : AuthRepository {

    override suspend fun postKakaoLogin(accessToken: String): Result<KakaoLoginModel> =
        suspendRunCatching {
            val data = authRemoteDataSource.postKakaoLogin(accessToken).checkData()

            data.token?.let { token ->
                localTokenDatasource.setAccessToken(token.accessToken)
                localTokenDatasource.setRefreshToken(token.refreshToken)
            }

            data.toModel()
        }

    override suspend fun postSignUp(
        accessToken: String,
        isServiceAgreed: Boolean,
        isPrivacyAgreed: Boolean,
        isMarketingAgreed: Boolean,
    ): Result<Unit> =
        suspendRunCatching {
            val token = authRemoteDataSource.postSignUp(
                accessToken = accessToken,
                isServiceAgreed = isServiceAgreed,
                isPrivacyAgreed = isPrivacyAgreed,
                isMarketingAgreed = isMarketingAgreed,
            ).checkData()

            localTokenDatasource.setAccessToken(token.accessToken)
            localTokenDatasource.setRefreshToken(token.refreshToken)
        }

    override suspend fun postLogout(): Result<Unit> =
        suspendRunCatching {
            val refreshToken = localTokenDatasource.getRefreshToken()
                ?: throw IllegalStateException("Refresh token not found.")

            authRemoteDataSource.postLogout(refreshToken).checkSuccess()
            localTokenDatasource.clearTokens()
        }
}
