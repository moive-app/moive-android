package com.moive.app.data.meeting.remote.service

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MeetingService {

    @POST("meetings")
    suspend fun postMeeting(
        @Body request: MeetingCreationRequest,
    ): BaseResponse<MeetingCreationResponse>
}
