package com.moive.app.data.votingstatus.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.votingstatus.remote.dto.ScheduleVoteResultResponse

interface VoteStatusRemoteDataSource {
    suspend fun getScheduleVoteResult(meetingId: Long): BaseResponse<ScheduleVoteResultResponse>
}
