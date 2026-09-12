package com.moive.app.data.voting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.voting.remote.dto.RecommendedAreaListResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceDetailResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceListResponse
import com.moive.app.data.voting.remote.service.VotingService
import javax.inject.Inject

class VotingRemoteDataSourceImpl @Inject constructor(
    private val votingService: VotingService,
) : VotingRemoteDataSource {

    override suspend fun getRecommendedAreas(meetingId: Long): BaseResponse<RecommendedAreaListResponse> =
        votingService.getRecommendedAreas(meetingId)

    override suspend fun getRecommendedPlaces(
        meetingId: Long,
        recommendedAreaId: Long,
    ): BaseResponse<RecommendedPlaceListResponse> =
        votingService.getRecommendedPlaces(meetingId, recommendedAreaId)

    override suspend fun getRecommendedPlaceDetail(
        meetingId: Long,
        recommendedAreaId: Long,
        recommendedPlaceId: Long,
    ): BaseResponse<RecommendedPlaceDetailResponse> =
        votingService.getRecommendedPlaceDetail(meetingId, recommendedAreaId, recommendedPlaceId)
}
