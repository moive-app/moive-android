package com.moive.app.presentation.meeting.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.home.mapper.toMeetingTab
import com.moive.app.data.meeting.repository.MeetingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MeetingListViewModel @Inject constructor(
    private val meetingRepository: MeetingRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingListContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        getMeetingList()
    }

    fun getMeetingList(loadMore: Boolean = false) = viewModelScope.launch {
        val currentState = _uiState.value
        if (loadMore && !currentState.hasNextMeetingList) return@launch

        val cursor = if (loadMore) currentState.nextCursor else null

        _uiState.update { it.copy(meetingListUiState = MeetingListUiState.Loading) }

        meetingRepository.getMeetingList(
            filter = currentState.selectedTab.toMeetingTab().name,
            cursor = cursor,
            size = DEFAULT_PAGE_SIZE,
        )
            .onSuccess { result ->
                _uiState.update {
                    val meetings = if (loadMore) it.meetingList + result.meetings else result.meetings
                    it.copy(
                        meetingList = meetings.toImmutableList(),
                        meetingListUiState = MeetingListUiState.Success,
                        nextCursor = result.nextCursor,
                        hasNextMeetingList = result.hasNext,
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(TAG).e(error)
                _uiState.update {
                    it.copy(meetingListUiState = MeetingListUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE))
                }
            }
    }

    fun postMeetingFilter(tab: String) {
        _uiState.update {
            it.copy(selectedTab = tab, nextCursor = null, hasNextMeetingList = true)
        }
        getMeetingList(loadMore = false)
    }

    companion object {
        private const val TAG = "MeetingList"
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
        private const val DEFAULT_PAGE_SIZE = 20
    }
}
