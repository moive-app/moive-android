package com.moive.app.data.voting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.voting.remote.dto.RecommendedAreaListResponse

interface VotingRemoteDataSource {
    suspend fun getRecommendedAreas(meetingId: Long): BaseResponse<RecommendedAreaListResponse>
}
