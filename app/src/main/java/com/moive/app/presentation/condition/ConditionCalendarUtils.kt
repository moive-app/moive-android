package com.moive.app.presentation.condition

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import java.util.Calendar

data class CalendarDay(
    val day: Int,
    val isCurrentMonth: Boolean,
)

fun buildCalendarDays(year: Int, month: Int): ImmutableList<CalendarDay> {
    val calendar = Calendar.getInstance()
    calendar.clear()
    calendar.set(year, month - 1, 1)

    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val previousMonthCalendar = calendar.clone() as Calendar
    previousMonthCalendar.add(Calendar.MONTH, -1)
    val daysInPreviousMonth = previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val leadingCount = firstDayOfWeek - 1
    val days = mutableListOf<CalendarDay>()

    for (i in 0 until leadingCount) {
        days.add(
            CalendarDay(
                day = daysInPreviousMonth - leadingCount + 1 + i,
                isCurrentMonth = false,
            ),
        )
    }

    for (day in 1..daysInMonth) {
        days.add(CalendarDay(day = day, isCurrentMonth = true))
    }

    val trailingCount = (7 - days.size % 7) % 7
    for (day in 1..trailingCount) {
        days.add(CalendarDay(day = day, isCurrentMonth = false))
    }

    return days.toPersistentList()
}

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
