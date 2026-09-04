package com.moive.app.core.designsystem.component.button

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class MoiveIconButtonSize(
    val padding: Dp,
    val iconSize: Dp,
) {
    LARGE(padding = 13.dp, iconSize = 24.dp),
    MEDIUM(padding = 11.dp, iconSize = 24.dp),
    SMALL(padding = 9.dp, iconSize = 20.dp),
}
