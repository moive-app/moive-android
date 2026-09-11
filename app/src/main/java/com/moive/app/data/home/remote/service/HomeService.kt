package com.moive.app.data.home.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.home.remote.dto.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("home")
    suspend fun getHome(
        @Query("filter") filter: String,
    ): BaseResponse<HomeResponse>
}
