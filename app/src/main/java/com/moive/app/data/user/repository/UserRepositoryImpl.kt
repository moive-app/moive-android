package com.moive.app.data.user.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.user.mapper.toModel
import com.moive.app.data.user.model.UserModel
import com.moive.app.data.user.remote.datasource.UserRemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getMyInfo(): Result<UserModel> =
        suspendRunCatching {
            userRemoteDataSource.getMyInfo().checkData().toModel()
        }
}
