package com.moive.app.data.voting.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.voting.remote.dto.RecommendedAreaListResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceDetailResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceListResponse
import com.moive.app.data.voting.remote.dto.RecommendedPlaceRouteResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VotingService {

    @GET("meetings/{meetingId}/recommendations/areas")
    suspend fun getRecommendedAreas(
        @Path("meetingId") meetingId: Long,
    ): BaseResponse<RecommendedAreaListResponse>

    @GET("meetings/{meetingId}/recommendations/areas/{recommendedAreaId}/places")
    suspend fun getRecommendedPlaces(
        @Path("meetingId") meetingId: Long,
        @Path("recommendedAreaId") recommendedAreaId: Long,
    ): BaseResponse<RecommendedPlaceListResponse>

    @GET("meetings/{meetingId}/recommendations/areas/{recommendedAreaId}/places/{recommendedPlaceId}")
    suspend fun getRecommendedPlaceDetail(
        @Path("meetingId") meetingId: Long,
        @Path("recommendedAreaId") recommendedAreaId: Long,
        @Path("recommendedPlaceId") recommendedPlaceId: Long,
    ): BaseResponse<RecommendedPlaceDetailResponse>

    @GET("meetings/{meetingId}/recommendations/places/{recommendedPlaceId}/routes/me")
    suspend fun getRecommendedPlaceRoute(
        @Path("meetingId") meetingId: Long,
        @Path("recommendedPlaceId") recommendedPlaceId: Long,
    ): BaseResponse<RecommendedPlaceRouteResponse>
}
