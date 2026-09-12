package com.moive.app.core.extensions

import java.text.BreakIterator

fun String.checkLength(): Int {
    if (isEmpty()) return 0

    val iterator = BreakIterator.getCharacterInstance()
    iterator.setText(this)

    var count = 0
    while (iterator.next() != BreakIterator.DONE)
        count++

    return count
}


fun String.parseDate(): String {
    val (_, month, day) = split("-").takeIf { it.size == 3 } ?: return this
    val monthNumber = month.toIntOrNull() ?: return this
    val dayNumber = day.toIntOrNull() ?: return this
    return "${monthNumber}월 ${dayNumber}일"
}

fun String.parseTime(): String {
    val (hour, minute) = split(":").takeIf { it.size == 2 } ?: return this
    val hourNumber = hour.toIntOrNull() ?: return this
    val minuteNumber = minute.toIntOrNull() ?: return this
    val period = if (hourNumber < 12) "오전" else "오후"
    val displayHour = when {
        hourNumber == 0 -> 12
        hourNumber > 12 -> hourNumber - 12
        else -> hourNumber
    }
    return "$period $displayHour:${minuteNumber.toString().padStart(2, '0')}"
}
