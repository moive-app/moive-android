package com.moive.app.data.meeting.mapper

import com.moive.app.core.extensions.parseDate
import com.moive.app.data.meeting.model.MeetingListCardItemModel
import com.moive.app.data.meeting.model.MeetingListModel
import com.moive.app.data.meeting.remote.dto.MeetingItemResponse
import com.moive.app.data.meeting.remote.dto.MeetingListResponse
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList

fun MeetingListResponse.toModel(): MeetingListModel =
    MeetingListModel(
        meetings = meetings.map { it.toModel() }.toImmutableList(),
        hasNext = hasNext,
        nextCursor = nextCursor,
    )

fun MeetingItemResponse.toModel(): MeetingListCardItemModel =
    MeetingListCardItemModel(
        id = meetingId,
        title = name,
        dateTime = if (scheduledDate != null && scheduledTime != null) {
            "${scheduledDate.parseDate()} ${scheduledTime.take(5)}"
        } else {
            null
        },
        participantImageUrls = participantProfileImages.toImmutableList(),
        statusText = statusLabel,
        statusLabelType = status.toLabelType(),
        extraParticipantCount = (participantCnt - participantProfileImages.size).coerceAtLeast(0),
    )
