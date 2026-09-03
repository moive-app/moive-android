package com.moive.app.core.extensions

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.then

fun InputTransformation.checkMaxLength(maxLength: Int): InputTransformation =
    this.then(CheckMaxLength(maxLength))

private data class CheckMaxLength(
    private val maxLength: Int
) : InputTransformation {

    override fun TextFieldBuffer.transformInput() {
        val length = asCharSequence().toString().checkLength()

        if (length > maxLength) {
            revertAllChanges()
        }
    }
}
