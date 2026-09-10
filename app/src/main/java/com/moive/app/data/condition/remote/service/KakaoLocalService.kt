package com.moive.app.data.condition.remote.service

import com.moive.app.data.condition.remote.dto.KakaoLocalSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoLocalService {

    @GET("v2/local/search/keyword.json")
    suspend fun searchKeyword(
        @Query("query") query: String,
    ): KakaoLocalSearchResponse
}
