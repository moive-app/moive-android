package com.moive.app.data.home.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.home.mapper.toModel
import com.moive.app.data.home.model.HomeModel
import com.moive.app.data.home.remote.datasource.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    override suspend fun getHome(filter: String): Result<HomeModel> =
        suspendRunCatching {
            homeRemoteDataSource.getHome(filter).checkData().toModel()
        }
}
