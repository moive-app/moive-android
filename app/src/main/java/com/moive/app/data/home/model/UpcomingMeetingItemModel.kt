package com.moive.app.data.home.model

import kotlinx.collections.immutable.ImmutableList

data class UpcomingMeetingItemModel(
    val id: Long,
    val title: String,
    val dateTime: String,
    val location: String,
    val participantImageList: ImmutableList<String>,
    val dDayText: String,
    val extraCount: Int,
)
