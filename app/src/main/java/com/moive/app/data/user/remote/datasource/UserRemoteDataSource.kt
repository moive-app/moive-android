package com.moive.app.data.user.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.user.remote.dto.UserResponse

interface UserRemoteDataSource {
    suspend fun getMyInfo(): BaseResponse<UserResponse>

    suspend fun deleteWithdraw(): BaseResponse<Unit>
}
