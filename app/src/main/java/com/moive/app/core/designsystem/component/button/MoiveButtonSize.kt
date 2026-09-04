package com.moive.app.core.designsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

enum class MoiveButtonSize(
    val verticalPadding: Dp,
) {
    LARGE(verticalPadding = 13.dp),
    MEDIUM(verticalPadding = 12.dp),
    SMALL(verticalPadding = 10.dp),
    XSMALL(verticalPadding = 7.dp);

    @Composable
    fun textStyle(): TextStyle = when (this) {
        LARGE -> typography.label.lgSb
        MEDIUM -> typography.label.mdSb
        SMALL -> typography.label.smM
        XSMALL -> typography.label.xsM
    }
}
