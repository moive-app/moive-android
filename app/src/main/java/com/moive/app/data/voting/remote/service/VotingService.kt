package com.moive.app.data.voting.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.voting.remote.dto.RecommendedAreaListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VotingService {

    @GET("meetings/{meetingId}/recommendations/areas")
    suspend fun getRecommendedAreas(
        @Path("meetingId") meetingId: Long,
    ): BaseResponse<RecommendedAreaListResponse>
}
