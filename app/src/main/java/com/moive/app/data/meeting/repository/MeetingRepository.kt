package com.moive.app.data.meeting.repository

import com.moive.app.data.meeting.model.MeetingCreationModel
import com.moive.app.data.meeting.mapper.MeetingPurposeType

interface MeetingRepository {
    suspend fun postMeetingCreation(
        name: String,
        hasSchedule: Boolean,
        scheduledDate: String?,
        scheduledTime: String?,
        purposeType: MeetingPurposeType,
    ): Result<MeetingCreationModel>
}
