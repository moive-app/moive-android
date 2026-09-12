package com.moive.app.data.votingstatus.repository

import com.moive.app.data.votingstatus.model.ScheduleVoteResultModel

interface VoteStatusRepository {
    suspend fun getScheduleVoteResult(meetingId: Long): Result<ScheduleVoteResultModel>
}
