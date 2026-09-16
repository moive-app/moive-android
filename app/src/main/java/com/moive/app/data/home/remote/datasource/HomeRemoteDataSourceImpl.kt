package com.moive.app.data.home.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.home.remote.dto.HomeResponse
import com.moive.app.data.home.remote.service.HomeService
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeRemoteDataSource {

    override suspend fun getHome(filter: String): BaseResponse<HomeResponse> =
        homeService.getHome(filter)
}
