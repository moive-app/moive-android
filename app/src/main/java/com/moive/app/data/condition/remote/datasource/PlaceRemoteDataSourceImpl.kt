package com.moive.app.data.condition.remote.datasource

import com.moive.app.data.condition.remote.dto.KakaoLocalSearchResponse
import com.moive.app.data.condition.remote.service.KakaoLocalService
import javax.inject.Inject

class PlaceRemoteDataSourceImpl @Inject constructor(
    private val kakaoLocalService: KakaoLocalService,
) : PlaceRemoteDataSource {

    override suspend fun getPlaceSearch(query: String): KakaoLocalSearchResponse =
        kakaoLocalService.searchKeyword(query)
}
