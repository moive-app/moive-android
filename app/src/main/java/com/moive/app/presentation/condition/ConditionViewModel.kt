package com.moive.app.presentation.condition

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.moive.app.core.designsystem.component.toast.ToastType
import com.moive.app.data.condition.model.AvailableScheduleModel
import com.moive.app.data.condition.repository.ConditionRepository
import com.moive.app.data.condition.repository.PlaceRepository
import com.moive.app.presentation.condition.ConditionContract.SideEffect
import com.moive.app.presentation.condition.ConditionContract.Step
import com.moive.app.presentation.condition.navigation.Condition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class ConditionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val placeRepository: PlaceRepository,
    private val conditionRepository: ConditionRepository,
) : ViewModel() {

    private val condition: Condition = savedStateHandle.toRoute<Condition>()
    private val meetingId: Long = condition.meetingId

    private val _uiState = MutableStateFlow(ConditionContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var job: Job? = null

    init {
        observePlaceSearchInput()
        fetchScheduledDate()
    }

    private fun fetchScheduledDate() {
        val scheduledDate = condition.scheduledDate ?: return
        if (!condition.hasSchedule) return

        val (year, month, day) = scheduledDate.split("-").map { it.toInt() }
        _uiState.update {
            it.copy(
                isDateConfirmed = true,
                confirmedDateTimes = persistentListOf(
                    DateTimeSelection(
                        year = year,
                        month = month,
                        day = day,
                        time = condition.scheduledTime.orEmpty(),
                    ),
                ),
            )
        }
    }

    fun onDateBoxClick() {
        if (_uiState.value.isDateConfirmed) return
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
        val state = _uiState.value
        val existingTime = state.confirmedDateTimes.firstOrNull {
            it.year == state.calendarYear && it.month == state.calendarMonth && it.day == day
        }?.time

        _uiState.update { it.copy(pendingDay = day, pendingTime = existingTime) }
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
        val state = _uiState.value
        val pending = state.pendingDateTime
        val isNewDate = pending != null && state.confirmedDateTimes.none {
            it.year == pending.year && it.month == pending.month && it.day == pending.day
        }

        if (isNewDate && state.confirmedDateTimes.size >= MAX_DATE_COUNT) {
            viewModelScope.launch {
                _sideEffect.send(SideEffect.OnShowToast(MAX_DATE_COUNT_MESSAGE, ToastType.CAUTION))
            }
            return
        }

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

    fun postCondition() {
        if (job?.isActive == true) return

        val currentState = _uiState.value
        val selectedPlace = currentState.selectedPlace
        val selectedTravelTime = currentState.selectedTravelTime

        if (selectedPlace == null || selectedTravelTime == null) {
            Timber.tag(TAG).e(CONDITION_SUBMIT_FAILURE_MESSAGE)
            return
        }

        val availableSchedules = currentState.confirmedDateTimes.map {
            AvailableScheduleModel(
                date = "%04d-%02d-%02d".format(it.year, it.month, it.day),
                time = it.time,
            )
        }
        val activityTypes = currentState.selectedPreferences.mapNotNull { it.toActivityType() }

        job = viewModelScope.launch {
            _uiState.update { it.copy(conditionUiState = ConditionUiState.Loading) }

            conditionRepository.postConditionInput(
                meetingId = meetingId,
                availableSchedules = availableSchedules,
                departureName = selectedPlace.address,
                departureLatitude = selectedPlace.latitude,
                departureLongitude = selectedPlace.longitude,
                maxTravelMinutes = selectedTravelTime.toMaxTravelMinutes(),
                activityTypes = activityTypes,
            )
                .onSuccess {
                    _uiState.update { it.copy(conditionUiState = ConditionUiState.Success) }
                    _sideEffect.send(SideEffect.NavigateToMeetingDetail)
                }
                .onFailure { error ->
                    Timber.tag(TAG).e(error, CONDITION_SUBMIT_FAILURE_MESSAGE)
                    _uiState.update {
                        it.copy(conditionUiState = ConditionUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE))
                    }
                }
        }
    }

    companion object {
        private const val TAG = "Condition"
        private const val SEARCH_NETWORK_DEBOUNCE = 300L
        private const val CONDITION_SUBMIT_FAILURE_MESSAGE = "조건 입력에 실패했습니다."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
        private const val MAX_DATE_COUNT_MESSAGE = "날짜는 최대 5개까지 선택 가능합니다."
    }
}
