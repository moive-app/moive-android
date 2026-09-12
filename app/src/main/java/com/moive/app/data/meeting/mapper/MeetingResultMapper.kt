package com.moive.app.data.meeting.mapper

import com.moive.app.data.meeting.model.MeetingResultModel
import com.moive.app.data.meeting.model.MeetingResultPlaceModel
import com.moive.app.data.meeting.model.MeetingResultParticipantModel
import com.moive.app.data.meeting.remote.dto.MeetingResultParticipantResponse
import com.moive.app.data.meeting.remote.dto.MeetingResultPlaceResponse
import com.moive.app.data.meeting.remote.dto.MeetingResultResponse

fun MeetingResultResponse.toModel(): MeetingResultModel =
    MeetingResultModel(
        place = place?.toModel(),
        meetingDate = meetingDate,
        meetingTime = meetingTime,
        participants = participants.mapIndexed { index, participant -> participant.toModel(id = index.toLong()) },
    )

fun MeetingResultPlaceResponse.toModel(): MeetingResultPlaceModel =
    MeetingResultPlaceModel(
        id = id,
        areaId = areaId,
        name = name,
        address = address,
        category = category,
        latitude = location.latitude,
        longitude = location.longitude,
    )

fun MeetingResultParticipantResponse.toModel(id: Long): MeetingResultParticipantModel =
    MeetingResultParticipantModel(
        id = id,
        name = nickname,
        profileImageUrl = profileImageUrl ?: "",
        address = address ?: "",
        transferCount = transferCnt ?: 0,
        travelMinutes = totalTime ?: 0,
    )
