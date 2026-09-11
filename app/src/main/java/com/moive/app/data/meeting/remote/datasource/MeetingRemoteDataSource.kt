package com.moive.app.data.meeting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse

interface MeetingRemoteDataSource {
    suspend fun postMeetingCreation(request: MeetingCreationRequest): BaseResponse<MeetingCreationResponse>
}
