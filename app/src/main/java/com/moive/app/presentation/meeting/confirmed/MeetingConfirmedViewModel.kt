package com.moive.app.presentation.meeting.confirmed

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MeetingConfirmedViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingConfirmedContract.State())
    val uiState = _uiState.asStateFlow()
}
