package com.moive.app.data.user.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.common.dto.checkSuccess
import com.moive.app.data.local.token.LocalTokenDataSource
import com.moive.app.data.user.mapper.toModel
import com.moive.app.data.user.model.UserModel
import com.moive.app.data.user.remote.datasource.UserRemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val localTokenDataSource: LocalTokenDataSource,
) : UserRepository {

    override suspend fun getMyInfo(): Result<UserModel> =
        suspendRunCatching {
            userRemoteDataSource.getMyInfo().checkData().toModel()
        }

    override suspend fun deleteWithdraw(): Result<Unit> =
        suspendRunCatching {
            userRemoteDataSource.deleteWithdraw().checkSuccess()
            localTokenDataSource.clearTokens()
        }
}
