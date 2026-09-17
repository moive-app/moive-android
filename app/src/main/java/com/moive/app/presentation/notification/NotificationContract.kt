package com.moive.app.presentation.notification

import androidx.compose.runtime.Immutable
import com.moive.app.data.notification.model.NotificationItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface NotificationContract {
    @Immutable
    data class State(
        val notificationUiState: NotificationUiState = NotificationUiState.Idle,
        val notifications: ImmutableList<NotificationItemModel> = persistentListOf(),
        val nextCursor: Long? = null,
        val hasNextNotifications: Boolean = true,
        val isNotificationPermissionGranted: Boolean = false,
    )
}

sealed interface NotificationUiState {
    data object Idle : NotificationUiState
    data object Loading : NotificationUiState
    data object Success : NotificationUiState
    data class Failure(
        val msg: String,
    ) : NotificationUiState
}
