package com.moive.app.data.voting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.voting.remote.dto.RecommendedAreaListResponse
import com.moive.app.data.voting.remote.service.VotingService
import javax.inject.Inject

class VotingRemoteDataSourceImpl @Inject constructor(
    private val votingService: VotingService,
) : VotingRemoteDataSource {

    override suspend fun getRecommendedAreas(meetingId: Long): BaseResponse<RecommendedAreaListResponse> =
        votingService.getRecommendedAreas(meetingId)
}
