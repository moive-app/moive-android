package com.moive.app.presentation.condition

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import java.util.Calendar

@Immutable
data class CalendarDay(
    val day: Int,
    val isCurrentMonth: Boolean,
    val isSelectable: Boolean,
)

private const val SELECTABLE_RANGE_DAYS = 42

fun buildCalendarDays(year: Int, month: Int): ImmutableList<CalendarDay> {
    val calendar = Calendar.getInstance()
    calendar.clear()
    calendar.set(year, month - 1, 1)

    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val previousMonthCalendar = calendar.clone() as Calendar
    previousMonthCalendar.add(Calendar.MONTH, -1)
    val daysInPreviousMonth = previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    val maxSelectableDate = (today.clone() as Calendar).apply {
        add(Calendar.DAY_OF_MONTH, SELECTABLE_RANGE_DAYS - 1)
    }

    val leadingCount = firstDayOfWeek - 1
    val days = mutableListOf<CalendarDay>()

    for (i in 0 until leadingCount) {
        days.add(
            CalendarDay(
                day = daysInPreviousMonth - leadingCount + 1 + i,
                isCurrentMonth = false,
                isSelectable = false,
            ),
        )
    }

    for (day in 1..daysInMonth) {
        val date = (calendar.clone() as Calendar).apply { set(Calendar.DAY_OF_MONTH, day) }
        val isSelectable = !date.before(today) && !date.after(maxSelectableDate)
        days.add(CalendarDay(day = day, isCurrentMonth = true, isSelectable = isSelectable))
    }

    val trailingCount = (7 - days.size % 7) % 7
    for (day in 1..trailingCount) {
        days.add(CalendarDay(day = day, isCurrentMonth = false, isSelectable = false))
    }

    return days.toPersistentList()
}

fun monthHasSelectableDay(year: Int, month: Int): Boolean =
    buildCalendarDays(year, month).any { it.isCurrentMonth && it.isSelectable }

fun previousMonth(year: Int, month: Int): Pair<Int, Int> {
    val calendar = Calendar.getInstance()
    calendar.clear()
    calendar.set(year, month - 1, 1)
    calendar.add(Calendar.MONTH, -1)
    return calendar.get(Calendar.YEAR) to (calendar.get(Calendar.MONTH) + 1)
}

fun nextMonth(year: Int, month: Int): Pair<Int, Int> {
    val calendar = Calendar.getInstance()
    calendar.clear()
    calendar.set(year, month - 1, 1)
    calendar.add(Calendar.MONTH, 1)
    return calendar.get(Calendar.YEAR) to (calendar.get(Calendar.MONTH) + 1)
}

fun buildTimeOptions(): ImmutableList<String> {
    val times = mutableListOf<String>()
    for (hour in 7 until 24) {
        val hourText = if (hour < 10) "0$hour" else "$hour"
        if (hour != 7) {
            times.add("$hourText:00")
        }
        times.add("$hourText:30")
    }
    return times.toPersistentList()
}
