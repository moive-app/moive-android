package com.moive.app.data.condition.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.condition.mapper.toModel
import com.moive.app.data.condition.model.PlaceSearchItemModel
import com.moive.app.data.condition.remote.datasource.PlaceRemoteDataSource
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeRemoteDataSource: PlaceRemoteDataSource,
) : PlaceRepository {

    override suspend fun getPlaceSearch(query: String): Result<List<PlaceSearchItemModel>> =
        suspendRunCatching {
            placeRemoteDataSource.getPlaceSearch(query).documents.map { it.toModel() }
        }
}
