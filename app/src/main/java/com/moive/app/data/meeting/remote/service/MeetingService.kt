package com.moive.app.data.meeting.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse
import com.moive.app.data.meeting.remote.dto.MeetingDetailResponse
import com.moive.app.data.meeting.remote.dto.MeetingListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MeetingService {

    @POST("meetings")
    suspend fun postMeeting(
        @Body request: MeetingCreationRequest,
    ): BaseResponse<MeetingCreationResponse>

    @GET("meetings")
    suspend fun getMeetings(
        @Query("filter") filter: String,
        @Query("cursor") cursor: Long?,
        @Query("size") size: Int,
    ): BaseResponse<MeetingListResponse>

    @GET("meetings/{meetingId}/home")
    suspend fun getMeetingDetail(
        @Path("meetingId") meetingId: Long,
    ): BaseResponse<MeetingDetailResponse>
}
