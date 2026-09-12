package com.moive.app.data.voting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.voting.remote.dto.PlaceVoteRequest
import com.moive.app.data.voting.remote.dto.RecommendedAreaListResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceDetailResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceListResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceRouteResponse

interface VotingRemoteDataSource {
    suspend fun getRecommendedAreas(meetingId: Long): BaseResponse<RecommendedAreaListResponse>

    suspend fun getRecommendedPlaces(
        meetingId: Long,
        recommendedAreaId: Long,
    ): BaseResponse<RecommendedPlaceListResponse>

    suspend fun getRecommendedPlaceDetail(
        meetingId: Long,
        recommendedAreaId: Long,
        recommendedPlaceId: Long,
    ): BaseResponse<RecommendedPlaceDetailResponse>

    suspend fun getRecommendedPlaceRoute(
        meetingId: Long,
        recommendedPlaceId: Long,
    ): BaseResponse<RecommendedPlaceRouteResponse>

    suspend fun postPlaceVotes(
        meetingId: Long,
        request: PlaceVoteRequest,
    ): BaseResponse<Unit>
}
