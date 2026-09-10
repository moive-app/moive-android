package com.moive.app.presentation.meeting.complete

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MeetingCompleteViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingCompleteContract.State())
    val uiState = _uiState.asStateFlow()
}
