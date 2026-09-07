package com.moive.app.data.home.model

import com.moive.app.core.designsystem.component.chip.LabelType
import kotlinx.collections.immutable.ImmutableList

data class MyMeetingCardItemModel(
    val id: Long,
    val title: String,
    val dateTime: String,
    val participantImageUrls: ImmutableList<String>,
    val statusText: String,
    val statusLabelType: LabelType,
    val extraParticipantCount: Int = 0,
)
