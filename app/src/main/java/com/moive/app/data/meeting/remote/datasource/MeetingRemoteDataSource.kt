package com.moive.app.data.meeting.remote.datasource

import com.moive.app.data.common.dto.BaseResponse
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import com.moive.app.data.meeting.remote.dto.MeetingCreationResponse
import com.moive.app.data.meeting.remote.dto.MeetingDetailResponse
import com.moive.app.data.meeting.remote.dto.MeetingListResponse

interface MeetingRemoteDataSource {
    suspend fun postMeetingCreation(request: MeetingCreationRequest): BaseResponse<MeetingCreationResponse>

    suspend fun getMeetingList(filter: String, cursor: Long?, size: Int): BaseResponse<MeetingListResponse>

    suspend fun getMeetingDetail(meetingId: Long): BaseResponse<MeetingDetailResponse>

    suspend fun deleteMeeting(meetingId: Long): BaseResponse<Unit>
}
