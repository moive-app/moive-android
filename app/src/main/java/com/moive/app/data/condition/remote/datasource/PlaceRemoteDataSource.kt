package com.moive.app.data.condition.remote.datasource

import com.moive.app.data.condition.remote.dto.KakaoLocalSearchResponse

interface PlaceRemoteDataSource {
    suspend fun getPlaceSearch(query: String): KakaoLocalSearchResponse
}
