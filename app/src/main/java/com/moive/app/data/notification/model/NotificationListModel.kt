package com.moive.app.data.notification.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class NotificationListModel(
    val notifications: ImmutableList<NotificationItemModel>,
    val hasNext: Boolean,
    val nextCursor: Long?,
)
