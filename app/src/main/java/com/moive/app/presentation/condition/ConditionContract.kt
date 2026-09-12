package com.moive.app.presentation.condition

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Immutable
import com.moive.app.core.designsystem.component.toast.ToastType
import com.moive.app.data.condition.mapper.ActivityType
import com.moive.app.data.condition.model.PlaceSearchItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import java.util.Calendar

interface ConditionContract {
    @Immutable
    data class State(
        val step: Step = Step.INPUT,
        val conditionUiState: ConditionUiState = ConditionUiState.Idle,
        val isDateConfirmed: Boolean = false,
        val isDateBottomSheetVisible: Boolean = false,
        val calendarYear: Int = Calendar.getInstance().get(Calendar.YEAR),
        val calendarMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,
        val pendingDay: Int? = null,
        val pendingTime: String? = null,
        val confirmedDateTimes: PersistentList<DateTimeSelection> = persistentListOf(),
        val searchFieldState: TextFieldState = TextFieldState(initialText = ""),
        val placeList: ImmutableList<PlaceSearchItemModel> = persistentListOf(),
        val selectedPlaceId: Long? = null,
        val selectedTravelTime: String? = null,
        val selectedPreferences: PersistentList<String> = persistentListOf(),
    ) {
        val travelTimeList: ImmutableList<String>
            get() = TRAVEL_TIME_OPTIONS

        val preferenceCategories: ImmutableList<PreferenceCategory>
            get() = PREFERENCE_CATEGORIES

        val calendarDays: ImmutableList<CalendarDay>
            get() = buildCalendarDays(calendarYear, calendarMonth)

        val timeOptions: ImmutableList<String>
            get() = TIME_OPTIONS

        val pendingDateTime: DateTimeSelection?
            get() = if (pendingDay != null && pendingTime != null) {
                DateTimeSelection(
                    year = calendarYear,
                    month = calendarMonth,
                    day = pendingDay,
                    time = pendingTime,
                )
            } else {
                null
            }

        val selectedDateTimes: PersistentList<DateTimeSelection>
            get() {
                val pending = pendingDateTime
                val entries = if (pending == null) {
                    confirmedDateTimes
                } else {
                    confirmedDateTimes
                        .filterNot { it.year == pending.year && it.month == pending.month && it.day == pending.day }
                        .toPersistentList()
                        .add(pending)
                }
                return entries
                    .sortedWith(compareBy({ it.year }, { it.month }, { it.day }, { it.time }))
                    .toPersistentList()
            }

        val selectedDateText: String?
            get() = confirmedDateTimes.firstOrNull()?.let { first ->
                if (confirmedDateTimes.size > 1) {
                    "${first.selectedDate} ${first.selectedTime} 외 ${confirmedDateTimes.size - 1}개"
                } else {
                    "${first.selectedDate} ${first.selectedTime}"
                }
            }

        val selectedPlace: PlaceSearchItemModel?
            get() = placeList.find { it.id == selectedPlaceId }

        val selectedPlaceText: String?
            get() = selectedPlace?.address

        val isNextButtonEnabled: Boolean
            get() = confirmedDateTimes.isNotEmpty() &&
                selectedPlaceId != null &&
                selectedTravelTime != null &&
                selectedPreferences.isNotEmpty()

        companion object {
            private val TIME_OPTIONS = buildTimeOptions()

            private val TRAVEL_TIME_OPTIONS = TravelTime.entries.map { it.label }.toPersistentList()

            private val PREFERENCE_CATEGORIES = persistentListOf(
                PreferenceCategory(
                    title = "놀거리",
                    items = persistentListOf("보드게임", "볼링", "방탈출", "노래방", "PC방", "만화카페"),
                ),
                PreferenceCategory(
                    title = "문화·관람",
                    items = persistentListOf("영화관", "공연", "전시·미술관", "박물관"),
                ),
                PreferenceCategory(
                    title = "먹고 마시기",
                    items = persistentListOf("한식", "양식", "중식", "일식", "고기", "해산물", "카페·디저트"),
                ),
                PreferenceCategory(
                    title = "나들이·쇼핑",
                    items = persistentListOf("공원", "산책", "등산", "쇼핑"),
                ),
            )
        }
    }

    enum class Step {
        INPUT,
        SEARCH,
        CONFIRM;
    }

    sealed class SideEffect {
        data object NavigateToMeetingDetail : SideEffect()
        data class OnShowToast(val message: String, val type: ToastType) : SideEffect()
    }
}

sealed interface ConditionUiState {
    data object Idle : ConditionUiState
    data object Loading : ConditionUiState
    data object Success : ConditionUiState
    data class Failure(
        val msg: String,
    ) : ConditionUiState
}

enum class TravelTime(val label: String, val maxMinutes: Int?) {
    WITHIN_30("30분 이내", 30),
    WITHIN_60("1시간 이내", 60),
    WITHIN_90("1시간 30분 이내", 90),
    NO_PREFERENCE("상관 없어요", null),
}

fun String.toMaxTravelMinutes(): Int? =
    TravelTime.entries.find { it.label == this }?.maxMinutes

fun String.toActivityType(): ActivityType? =
    ActivityType.entries.find { it.label == this }

@Immutable
data class PreferenceCategory(
    val title: String,
    val items: ImmutableList<String>,
)

@Immutable
data class DateTimeSelection(
    val year: Int,
    val month: Int,
    val day: Int,
    val time: String,
) {
    val selectedDate: String
        get() = "${month}월 ${day}일"

    val selectedTime: String
        get() = time
}
