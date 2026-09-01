package com.moive.app.core.designsystem.component.chip

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ChipType(
    val verticalPadding: Dp,
    val horizontalPadding: Dp,
) {
    TAB(
        verticalPadding = 8.dp,
        horizontalPadding = 16.dp,
    ),
    SELECT(
        verticalPadding = 7.dp,
        horizontalPadding = 12.dp,
    ),
}
