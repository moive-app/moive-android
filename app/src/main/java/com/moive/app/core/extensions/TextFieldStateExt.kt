package com.moive.app.core.extensions

import androidx.compose.foundation.text.input.TextFieldState

fun TextFieldState.trim() {
    edit { replace(0, length, asCharSequence().toString().trim()) }
}
