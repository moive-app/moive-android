package com.moive.app.data.votingstatus.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.votingstatus.remote.dto.ScheduleVoteResultResponse
import com.moive.app.data.votingstatus.remote.service.VoteStatusService
import javax.inject.Inject

class VoteStatusRemoteDataSourceImpl @Inject constructor(
    private val voteStatusService: VoteStatusService,
) : VoteStatusRemoteDataSource {

    override suspend fun getScheduleVoteResult(meetingId: Long): BaseResponse<ScheduleVoteResultResponse> =
        voteStatusService.getScheduleVoteResult(meetingId)
}
