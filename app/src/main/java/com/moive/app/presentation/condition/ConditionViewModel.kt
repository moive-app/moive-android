package com.moive.app.presentation.condition

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.condition.repository.PlaceRepository
import com.moive.app.presentation.condition.ConditionContract.Step
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class ConditionViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConditionContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        observePlaceSearchInput()
    }

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

    fun onDateTimeChipClick(entry: DateTimeSelection) {
        _uiState.update { state ->
            val isPending = state.calendarYear == entry.year &&
                state.calendarMonth == entry.month &&
                state.pendingDay == entry.day
            state.copy(
                pendingDay = if (isPending) null else state.pendingDay,
                pendingTime = if (isPending) null else state.pendingTime,
                confirmedDateTimes = state.confirmedDateTimes
                    .filterNot { it.year == entry.year && it.month == entry.month && it.day == entry.day }
                    .toPersistentList(),
            )
        }
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

    @OptIn(FlowPreview::class)
    private fun observePlaceSearchInput() = viewModelScope.launch {
        snapshotFlow { _uiState.value.searchFieldState.text }
            .debounce(SEARCH_NETWORK_DEBOUNCE.milliseconds)
            .distinctUntilChanged()
            .collectLatest { searchInputText ->
                val text = searchInputText.toString()
                if (text.isBlank()) {
                    _uiState.update {
                        it.copy(
                            placeList = persistentListOf(),
                        )
                    }
                } else {
                    postPlaceSearch(text)
                }
            }
    }

    fun postPlaceSearch(query: String) = viewModelScope.launch {
        placeRepository.getPlaceSearch(query)
            .onSuccess { places ->
                _uiState.update { it.copy(placeList = places.toImmutableList()) }
            }
            .onFailure { throwable ->
                Timber.tag(TAG).e(throwable, "장소 검색 실패")
                _uiState.update { it.copy(placeList = persistentListOf()) }
            }
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

    companion object {
        private const val TAG = "Condition"
        private const val SEARCH_NETWORK_DEBOUNCE = 300L

    }
}
