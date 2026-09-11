package com.moive.app.data.meeting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse
import com.moive.app.data.meeting.remote.service.MeetingService
import javax.inject.Inject

class MeetingRemoteDataSourceImpl @Inject constructor(
    private val meetingService: MeetingService,
) : MeetingRemoteDataSource {

    override suspend fun postMeetingCreation(request: MeetingCreationRequest): BaseResponse<MeetingCreationResponse> =
        meetingService.postMeeting(request)
}
