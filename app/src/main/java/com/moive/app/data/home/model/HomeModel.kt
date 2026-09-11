package com.moive.app.data.home.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class HomeModel(
    val confirmedMeetings: ImmutableList<ConfirmedMeetingItemModel>,
    val myMeetings: ImmutableList<MyMeetingCardItemModel>,
)
