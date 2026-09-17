package com.moive.app.presentation.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.notification.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        getNotificationList()
    }

    fun getNotificationList(loadMore: Boolean = false) = viewModelScope.launch {
        val currentState = _uiState.value
        if (loadMore && !currentState.hasNextNotifications) return@launch

        val cursor = if (loadMore) currentState.nextCursor else null

        _uiState.update { it.copy(notificationUiState = NotificationUiState.Loading) }

        notificationRepository.getNotificationList(
            cursor = cursor,
            size = DEFAULT_PAGE_SIZE,
        )
            .onSuccess { result ->
                _uiState.update {
                    val notifications =
                        if (loadMore) it.notifications + result.notifications else result.notifications
                    it.copy(
                        notifications = notifications.toImmutableList(),
                        notificationUiState = NotificationUiState.Success,
                        nextCursor = result.nextCursor,
                        hasNextNotifications = result.hasNext,
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error)
                _uiState.update {
                    it.copy(notificationUiState = NotificationUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE))
                }
            }
    }

    fun onNotificationPermissionChanged(isGranted: Boolean) {
        _uiState.update { it.copy(isNotificationPermissionGranted = isGranted) }
    }

    fun patchNotificationReadStatus(notificationId: Long) {
        viewModelScope.launch {
            notificationRepository.patchNotificationRead(notificationId)
                .onSuccess {
                    _uiState.update { state ->
                        state.copy(
                            notifications = state.notifications
                                .map { if (it.id == notificationId) it.copy(isRead = true) else it }
                                .toImmutableList(),
                        )
                    }
                }
                .onFailure { error ->
                    Timber.tag(TAG).e(error)
                }
        }
    }

    companion object {
        private const val TAG = "Notification"
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
        private const val DEFAULT_PAGE_SIZE = 20
    }
}
