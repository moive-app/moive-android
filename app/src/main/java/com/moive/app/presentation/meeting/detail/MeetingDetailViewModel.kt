package com.moive.app.presentation.meeting.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MeetingDetailViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(MeetingDetailContract.State())
    val uiState = _uiState.asStateFlow()

    fun showLeaveMeetingDialog() {
        _uiState.update { it.copy(isLeaveMeetingDialogVisible = true) }
    }

    fun dismissLeaveMeetingDialog() {
        _uiState.update { it.copy(isLeaveMeetingDialogVisible = false) }
    }

    fun deleteMeeting() {
        // TODO: 모임 나가기 API 연동
    }

}

