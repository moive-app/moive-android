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
