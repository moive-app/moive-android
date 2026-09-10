package com.moive.app.data.condition.repository

import com.moive.app.data.condition.model.PlaceSearchItemModel

interface PlaceRepository {
    suspend fun getPlaceSearch(query: String): Result<List<PlaceSearchItemModel>>
}
