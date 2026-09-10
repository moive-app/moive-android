package com.moive.app.presentation.condition

import androidx.lifecycle.ViewModel
import com.moive.app.presentation.condition.ConditionContract.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConditionViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(ConditionContract.State())
    val uiState = _uiState.asStateFlow()

    fun onDateBoxClick() {
        _uiState.update { it.copy(isDateBottomSheetVisible = true) }
    }

    fun dismissDateBottomSheet() {
        _uiState.update {
            it.copy(
                isDateBottomSheetVisible = false,
                pendingDay = null,
                pendingTime = null,
            )
        }
    }

    fun onCalendarPrevMonthClick() {
        _uiState.update {
            val (year, month) = previousMonth(it.calendarYear, it.calendarMonth)
            it.copy(calendarYear = year, calendarMonth = month, pendingDay = null)
        }
    }

    fun onCalendarNextMonthClick() {
        _uiState.update {
            val (year, month) = nextMonth(it.calendarYear, it.calendarMonth)
            it.copy(calendarYear = year, calendarMonth = month, pendingDay = null)
        }
    }

    fun onCalendarDayClick(day: Int) {
        _uiState.update { state ->
            val existingTime = state.confirmedDateTimes.firstOrNull {
                it.year == state.calendarYear && it.month == state.calendarMonth && it.day == day
            }?.time
            state.copy(pendingDay = day, pendingTime = existingTime)
        }
    }

    fun onCalendarTimeClick(time: String) {
        _uiState.update { it.copy(pendingTime = time) }
    }

    fun onDateNextClick() {
        _uiState.update {
            it.copy(
                confirmedDateTimes = it.selectedDateTimes,
                pendingDay = null,
                pendingTime = null,
            )
        }
    }

    fun onDateSaveClick() {
        _uiState.update {
            it.copy(
                isDateBottomSheetVisible = false,
                confirmedDateTimes = it.selectedDateTimes,
                pendingDay = null,
                pendingTime = null,
            )
        }
    }

    fun onPlaceSearchBoxClick() {
        _uiState.update { it.copy(step = Step.SEARCH) }
    }

    fun postPlaceSearch() {
        // TODO: 위치 검색 API 연동
    }

    fun onPlaceItemClick(placeId: Long) {
        _uiState.update {
            it.copy(
                step = Step.INPUT,
                selectedPlaceId = placeId,
            )
        }
    }

    fun onTravelTimeClick(travelTime: String) {
        _uiState.update { it.copy(selectedTravelTime = travelTime) }
    }

    fun onPreferenceClick(preference: String) {
        _uiState.update {
            val selected = it.selectedPreferences
            it.copy(
                selectedPreferences = if (preference in selected) {
                    selected.remove(preference)
                } else {
                    selected.add(preference)
                },
            )
        }
    }

    fun onNextButtonClick() {
        _uiState.update { it.copy(step = Step.CONFIRM) }
    }

    fun backToInputStep() {
        _uiState.update { it.copy(step = Step.INPUT) }
    }
}
