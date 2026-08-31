package com.moive.app.data.auth.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.auth.mapper.toModel
import com.moive.app.data.auth.model.KakaoLoginModel
import com.moive.app.data.auth.remote.datasource.AuthRemoteDataSource
import com.moive.app.data.common.dto.checkData
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
}
