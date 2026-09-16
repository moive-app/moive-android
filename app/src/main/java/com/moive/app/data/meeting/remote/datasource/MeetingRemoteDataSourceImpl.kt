package com.moive.app.data.meeting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse
import com.moive.app.data.meeting.remote.dto.MeetingDetailResponse
import com.moive.app.data.meeting.remote.dto.MeetingJoinResponse
import com.moive.app.data.meeting.remote.dto.MeetingListResponse
import com.moive.app.data.meeting.remote.dto.MeetingResultResponse
import com.moive.app.data.meeting.remote.service.MeetingService
import javax.inject.Inject

class MeetingRemoteDataSourceImpl @Inject constructor(
    private val meetingService: MeetingService,
) : MeetingRemoteDataSource {

    override suspend fun postMeetingCreation(request: MeetingCreationRequest): BaseResponse<MeetingCreationResponse> =
        meetingService.postMeeting(request)

    override suspend fun getMeetingList(filter: String, cursor: Long?, size: Int): BaseResponse<MeetingListResponse> =
        meetingService.getMeetings(filter, cursor, size)

    override suspend fun getMeetingDetail(meetingId: Long): BaseResponse<MeetingDetailResponse> =
        meetingService.getMeetingDetail(meetingId)

    override suspend fun getMeetingResult(meetingId: Long): BaseResponse<MeetingResultResponse> =
        meetingService.getMeetingResult(meetingId)

    override suspend fun deleteMeeting(meetingId: Long): BaseResponse<Unit> =
        meetingService.deleteMeeting(meetingId)

    override suspend fun postMeetingJoin(inviteCode: String): BaseResponse<MeetingJoinResponse> =
        meetingService.postMeetingJoin(inviteCode)
}
