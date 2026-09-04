package com.moive.app.presentation.meeting.detail

import androidx.lifecycle.ViewModel
import com.moive.app.presentation.meeting.detail.MeetingDetailContract.MeetingStatus
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

    fun changeStatus() {
        _uiState.update { state ->
            val nextStatus = when (state.status) {
                MeetingStatus.INPUTTING -> MeetingStatus.VOTING
                MeetingStatus.VOTING -> MeetingStatus.CONFIRMED
                MeetingStatus.CONFIRMED -> MeetingStatus.CONFIRMED
            }
            state.copy(status = nextStatus)
        }
    }
}
