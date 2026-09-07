package com.moive.app.presentation.meeting.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MeetingListViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingListContract.State())
    val uiState = _uiState.asStateFlow()

    fun postMeetingFilter(tab: String) {
        // TODO: 모임 필터 조회 API 연동
        _uiState.update {
            it.copy(selectedTab = tab)
        }
    }
}
