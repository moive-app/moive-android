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
