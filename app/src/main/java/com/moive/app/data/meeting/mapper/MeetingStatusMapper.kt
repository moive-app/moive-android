package com.moive.app.data.meeting.mapper

import com.moive.app.core.designsystem.component.chip.LabelType

fun String.toLabelType(): LabelType = when (this) {
    "CONDITION_INPUT" -> LabelType.CONDITION
    "VOTING" -> LabelType.VOTING
    "CONFIRMED" -> LabelType.CONFIRMED
    "COMPLETED" -> LabelType.COMPLETE
    else -> LabelType.CONDITION
}
