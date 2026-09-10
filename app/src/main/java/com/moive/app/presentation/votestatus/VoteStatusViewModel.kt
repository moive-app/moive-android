package com.moive.app.presentation.votestatus

import androidx.lifecycle.ViewModel
import com.moive.app.presentation.votestatus.VoteStatusContract.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VoteStatusViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(VoteStatusContract.State())
    val uiState = _uiState.asStateFlow()

    fun onPlaceItemClick(placeId: Long) {
        _uiState.update { it.copy(step = Step.DETAIL, currentPlaceId = placeId) }
    }

    fun backToList() {
        _uiState.update { it.copy(step = Step.LIST) }
    }

    fun onKakaoMapRouteOpened(opened: Boolean) {
        if (opened) return
        Timber.tag("VoteStatus").e("카카오 맵을 열 수 없습니다.")
    }
}
