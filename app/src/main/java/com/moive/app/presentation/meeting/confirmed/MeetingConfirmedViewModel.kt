package com.moive.app.presentation.meeting.confirmed

import androidx.lifecycle.ViewModel
import com.moive.app.presentation.meeting.confirmed.MeetingConfirmedContract.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MeetingConfirmedViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MeetingConfirmedContract.State())
    val uiState = _uiState.asStateFlow()

    fun onPlaceClick() {
        _uiState.update { it.copy(step = Step.DETAIL) }
    }

    fun backToMain() {
        _uiState.update { it.copy(step = Step.MAIN) }
    }

    fun onKakaoMapRouteOpened(opened: Boolean) {
        if (opened) return
        Timber.tag("MeetingConfirmed").e("카카오 맵을 열 수 없습니다.")
    }
}
