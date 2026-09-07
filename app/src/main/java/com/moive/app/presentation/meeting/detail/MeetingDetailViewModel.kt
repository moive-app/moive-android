package com.moive.app.presentation.meeting.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MeetingDetailViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(MeetingDetailContract.State())
    val uiState = _uiState.asStateFlow()

}
