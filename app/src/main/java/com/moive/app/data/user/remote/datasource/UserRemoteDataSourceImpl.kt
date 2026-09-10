package com.moive.app.data.user.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.user.remote.dto.UserResponse
import com.moive.app.data.user.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserRemoteDataSource {

    override suspend fun getMyInfo(): BaseResponse<UserResponse> =
        userService.getMyInfo()

    override suspend fun deleteWithdraw(): BaseResponse<Unit> =
        userService.deleteWithdraw()
}
