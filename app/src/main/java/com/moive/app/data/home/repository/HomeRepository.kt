package com.moive.app.data.home.repository

import com.moive.app.data.home.model.HomeModel

interface HomeRepository {
    suspend fun getHome(filter: String): Result<HomeModel>
}
