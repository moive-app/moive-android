package com.moive.app.data.user.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.user.remote.dto.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.GET

interface UserService {

    @GET("users/me")
    suspend fun getMyInfo(): BaseResponse<UserResponse>

    @DELETE("users/me")
    suspend fun deleteWithdraw(): BaseResponse<Unit>
}
