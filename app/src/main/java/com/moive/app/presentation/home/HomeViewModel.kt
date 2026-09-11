package com.moive.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.home.mapper.MeetingTab
import com.moive.app.data.home.mapper.toMeetingTab
import com.moive.app.data.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContract.State())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        getHome(MeetingTab.ALL)
    }

    private fun getHome(tab: MeetingTab) {
        job?.cancel()
        job = viewModelScope.launch {
            _uiState.update { it.copy(homeUiState = HomeUiState.Loading) }

            homeRepository.getHome(filter = tab.name)
                .onSuccess { home ->
                    _uiState.update {
                        it.copy(
                            homeUiState = HomeUiState.Success,
                            upcomingMeetings = home.confirmedMeetings,
                            myMeetingList = home.myMeetings,
                        )
                    }
                }
                .onFailure { error ->
                    Timber.tag(TAG).e(error)
                    _uiState.update {
                        it.copy(homeUiState = HomeUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE))
                    }
                }
        }
    }

    fun postMeetingFilter(tab: String) {
        _uiState.update { it.copy(selectedTab = tab) }
        getHome(tab.toMeetingTab())
    }

    companion object {
        private const val TAG = "Home"
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
