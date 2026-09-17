package com.moive.app.core.extensions

import java.text.BreakIterator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
    val hourNumber = hour.toIntOrNull()?.takeIf { it in 0..23 } ?: return this
    val minuteNumber = minute.toIntOrNull()?.takeIf { it in 0..59 } ?: return this
    val period = if (hourNumber < 12) "오전" else "오후"
    val displayHour = when {
        hourNumber == 0 -> 12
        hourNumber > 12 -> hourNumber - 12
        else -> hourNumber
    }
    return "$period $displayHour:${minuteNumber.toString().padStart(2, '0')}"
}

fun String.toRelativeTime(): String {
    val createdAt = runCatching {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA).parse(this)
    }.getOrNull() ?: return this

    val diffMinutes = (System.currentTimeMillis() - createdAt.time).coerceAtLeast(0) / MILLIS_PER_MINUTE
    val diffHours = diffMinutes / MINUTES_PER_HOUR
    val diffDays = diffHours / HOURS_PER_DAY

    return when {
        diffMinutes < 1 -> "방금 전"
        diffMinutes < MINUTES_PER_HOUR -> "${diffMinutes}분 전"
        diffHours < HOURS_PER_DAY -> "${diffHours}시간 전"
        diffDays < DAYS_PER_WEEK -> "${diffDays}일 전"
        else -> {
            val calendar = Calendar.getInstance().apply { time = createdAt }
            "${calendar.get(Calendar.MONTH) + 1}월 ${calendar.get(Calendar.DAY_OF_MONTH)}일"
        }
    }
}

private const val MILLIS_PER_MINUTE = 60_000L
private const val MINUTES_PER_HOUR = 60L
private const val HOURS_PER_DAY = 24L
private const val DAYS_PER_WEEK = 7L
