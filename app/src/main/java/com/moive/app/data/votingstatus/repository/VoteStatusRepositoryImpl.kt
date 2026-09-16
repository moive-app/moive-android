package com.moive.app.data.votingstatus.repository

import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.common.dto.checkData
import com.moive.app.data.votingstatus.mapper.toModel
import com.moive.app.data.votingstatus.model.PlaceVoteResultModel
import com.moive.app.data.votingstatus.model.ScheduleVoteResultModel
import com.moive.app.data.votingstatus.remote.datasource.VoteStatusRemoteDataSource
import javax.inject.Inject

class VoteStatusRepositoryImpl @Inject constructor(
    private val voteStatusRemoteDataSource: VoteStatusRemoteDataSource,
) : VoteStatusRepository {

    override suspend fun getScheduleVoteResult(meetingId: Long): Result<ScheduleVoteResultModel> =
        suspendRunCatching {
            voteStatusRemoteDataSource.getScheduleVoteResult(meetingId)
                .checkData()
                .toModel()
        }

    override suspend fun getPlaceVoteResult(meetingId: Long): Result<PlaceVoteResultModel> =
        suspendRunCatching {
            voteStatusRemoteDataSource.getPlaceVoteResult(meetingId)
                .checkData()
                .toModel()
        }
}
