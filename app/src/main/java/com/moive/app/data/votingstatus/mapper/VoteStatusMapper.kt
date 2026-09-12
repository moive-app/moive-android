package com.moive.app.data.votingstatus.mapper

import com.moive.app.data.votingstatus.model.PlaceVoteCandidateModel
import com.moive.app.data.votingstatus.model.PlaceVoteResultModel
import com.moive.app.data.votingstatus.model.ScheduleVoteCandidateModel
import com.moive.app.data.votingstatus.model.ScheduleVoteResultModel
import com.moive.app.data.votingstatus.remote.dto.PlaceVoteCandidateItem
import com.moive.app.data.votingstatus.remote.dto.PlaceVoteResultResponse
import com.moive.app.data.votingstatus.remote.dto.ScheduleVoteCandidateItem
import com.moive.app.data.votingstatus.remote.dto.ScheduleVoteResultResponse

fun ScheduleVoteResultResponse.toModel(): ScheduleVoteResultModel =
    ScheduleVoteResultModel(
        isVoteSkipped = isVoteSkipped,
        totalVoterCount = totalVoterCnt,
        candidates = candidates.map { it.toModel() },
    )

fun ScheduleVoteCandidateItem.toModel(): ScheduleVoteCandidateModel =
    ScheduleVoteCandidateModel(
        meetingDate = meetingDate,
        meetingTime = meetingTime,
        voterCount = voterCnt,
        isVotedByMe = isVotedByMe,
    )

fun PlaceVoteResultResponse.toModel(): PlaceVoteResultModel =
    PlaceVoteResultModel(
        isFinished = isFinished,
        totalVoterCount = totalVoterCnt,
        candidates = candidates.map { it.toModel() },
    )

fun PlaceVoteCandidateItem.toModel(): PlaceVoteCandidateModel =
    PlaceVoteCandidateModel(
        id = placeId,
        placeName = placeName,
        voterCount = voterCnt,
        isVotedByMe = isVotedByMe,
    )
