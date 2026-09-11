package com.moive.app.data.meeting.repository

import com.moive.app.data.meeting.mapper.MeetingPurposeType
import com.moive.app.data.meeting.model.MeetingCreationModel
import com.moive.app.data.meeting.model.MeetingListModel

interface MeetingRepository {
    suspend fun postMeetingCreation(
        name: String,
        hasSchedule: Boolean,
        scheduledDate: String?,
        scheduledTime: String?,
        purposeType: MeetingPurposeType,
    ): Result<MeetingCreationModel>

    suspend fun getMeetingList(filter: String, cursor: Long?, size: Int): Result<MeetingListModel>
}
