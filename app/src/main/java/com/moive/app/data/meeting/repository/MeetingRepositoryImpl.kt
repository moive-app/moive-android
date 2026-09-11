package com.moive.app.data.meeting.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.meeting.mapper.toModel
import com.moive.app.data.meeting.model.MeetingCreationModel
import com.moive.app.data.meeting.model.MeetingDetailModel
import com.moive.app.data.meeting.model.MeetingListModel
import com.moive.app.data.meeting.mapper.MeetingPurposeType
import com.moive.app.data.meeting.remote.datasource.MeetingRemoteDataSource
import com.moive.app.data.meeting.remote.dto.MeetingCreationRequest
import javax.inject.Inject

class MeetingRepositoryImpl @Inject constructor(
    private val meetingRemoteDataSource: MeetingRemoteDataSource,
) : MeetingRepository {

    override suspend fun postMeetingCreation(
        name: String,
        hasSchedule: Boolean,
        scheduledDate: String?,
        scheduledTime: String?,
        purposeType: MeetingPurposeType,
    ): Result<MeetingCreationModel> =
        suspendRunCatching {
            meetingRemoteDataSource.postMeetingCreation(
                MeetingCreationRequest(
                    name = name,
                    hasSchedule = hasSchedule,
                    scheduledDate = scheduledDate,
                    scheduledTime = scheduledTime,
                    purposeType = purposeType,
                )
            ).checkData().toModel()
        }

    override suspend fun getMeetingList(filter: String, cursor: Long?, size: Int): Result<MeetingListModel> =
        suspendRunCatching {
            meetingRemoteDataSource.getMeetingList(filter, cursor, size).checkData().toModel()
        }

    override suspend fun getMeetingDetail(meetingId: Long): Result<MeetingDetailModel> =
        suspendRunCatching {
            meetingRemoteDataSource.getMeetingDetail(meetingId).checkData().toModel()
        }
}
