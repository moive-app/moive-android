package com.moive.app.data.meeting.model

import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.chip.LabelType
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class MeetingListModel(
    val meetings: ImmutableList<MeetingListCardItemModel>,
    val hasNext: Boolean,
    val nextCursor: Long?,
)

@Immutable
data class MeetingListCardItemModel(
    val id: Long,
    val title: String,
    val dateTime: String?,
    val participantImageUrls: ImmutableList<String>,
    val statusText: String,
    val statusLabelType: LabelType,
    val extraParticipantCount: Int = 0,
)
