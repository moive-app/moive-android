package com.moive.app.data.meeting.mapper

import com.moive.app.core.designsystem.component.chip.LabelType

enum class MeetingStatus {
    CONDITION_INPUT,
    VOTING,
    CONFIRMED,
    COMPLETED,
}

fun String.toMeetingStatus(): MeetingStatus =
    MeetingStatus.entries.find { it.name == this } ?: MeetingStatus.CONDITION_INPUT

fun String.toLabelType(): LabelType = when (toMeetingStatus()) {
    MeetingStatus.CONDITION_INPUT -> LabelType.CONDITION
    MeetingStatus.VOTING -> LabelType.VOTING
    MeetingStatus.CONFIRMED -> LabelType.CONFIRMED
    MeetingStatus.COMPLETED -> LabelType.COMPLETE
}
