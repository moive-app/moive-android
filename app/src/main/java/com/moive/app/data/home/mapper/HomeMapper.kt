package com.moive.app.data.home.mapper

import com.moive.app.core.extensions.parseDate
import com.moive.app.data.home.model.ConfirmedMeetingItemModel
import com.moive.app.data.home.model.HomeModel
import com.moive.app.data.home.model.MyMeetingCardItemModel
import com.moive.app.data.home.remote.dto.ConfirmedMeetingResponse
import com.moive.app.data.home.remote.dto.HomeResponse
import com.moive.app.data.home.remote.dto.MyMeetingResponse
import com.moive.app.data.meeting.mapper.toLabelType
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList

fun HomeResponse.toModel(): HomeModel =
    HomeModel(
        confirmedMeetings = confirmedMeetings.map { it.toModel() }.toImmutableList(),
        myMeetings = myMeetings.map { it.toModel() }.toImmutableList(),
    )

fun ConfirmedMeetingResponse.toModel(): ConfirmedMeetingItemModel =
    ConfirmedMeetingItemModel(
        id = meetingId,
        title = name,
        dateTime = "${confirmedDate.parseDate()} ${confirmedTime.take(5)}",
        location = confirmedPlaceName,
        participantImageList = participantProfileImages.toImmutableList(),
        dDayText = "D-$dDay",
        extraCount = (participantCnt - participantProfileImages.size).coerceAtLeast(0),
    )

fun MyMeetingResponse.toModel(): MyMeetingCardItemModel =
    MyMeetingCardItemModel(
        id = meetingId,
        title = name,
        dateTime = if (scheduledDate != null && scheduledTime != null) {
            "${scheduledDate.parseDate()} ${scheduledTime.take(5)}"
        } else {
            null
        },
        participantImageUrls = participantProfileImages.toPersistentList(),
        statusText = statusLabel,
        statusLabelType = status.toLabelType(),
        extraParticipantCount = (participantCnt - participantProfileImages.size).coerceAtLeast(0),
    )

enum class MeetingTab(val label: String) {
    ALL("전체"),
    UPCOMING("예정"),
    PAST("지난 모임"),
}

fun String.toMeetingTab(): MeetingTab =
    MeetingTab.entries.find { it.label == this } ?: MeetingTab.ALL
