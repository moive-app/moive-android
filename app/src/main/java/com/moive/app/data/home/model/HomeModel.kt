package com.moive.app.data.home.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class HomeModel(
    val nickname: String,
    val confirmedMeetings: ImmutableList<ConfirmedMeetingItemModel>,
    val myMeetings: ImmutableList<MyMeetingCardItemModel>,
)
