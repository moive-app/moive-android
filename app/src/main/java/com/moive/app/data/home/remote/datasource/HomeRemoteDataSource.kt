package com.moive.app.data.home.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.home.remote.dto.HomeResponse

interface HomeRemoteDataSource {
    suspend fun getHome(filter: String): BaseResponse<HomeResponse>
}
