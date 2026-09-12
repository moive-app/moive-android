package com.moive.app.data.votingstatus.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.votingstatus.remote.dto.PlaceVoteResultResponse
import com.moive.app.data.votingstatus.remote.dto.ScheduleVoteResultResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VoteStatusService {
    @GET("meetings/{meetingId}/date-votes/result")
    suspend fun getScheduleVoteResult(
        @Path("meetingId") meetingId: Long,
    ): BaseResponse<ScheduleVoteResultResponse>

    @GET("meetings/{meetingId}/place-votes/result")
    suspend fun getPlaceVoteResult(
        @Path("meetingId") meetingId: Long,
    ): BaseResponse<PlaceVoteResultResponse>
}
