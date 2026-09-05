package com.moive.app.core.designsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moive.app.core.designsystem.theme.MoiveTheme.colors

data class MoiveButtonStyle(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color? = null,
)

enum class MoiveButtonType {
    PRIMARY,
    SECONDARY,
    TERTIARY;

    @Composable
    fun getStyle(
        enabled: Boolean,
        isPressed: Boolean,
    ): MoiveButtonStyle = when (this) {
        PRIMARY -> when {
            !enabled -> MoiveButtonStyle(
                containerColor = colors.fill.default06,
                contentColor = colors.text.disabled,
            )
            isPressed -> MoiveButtonStyle(
                containerColor = colors.primary.pressed,
                contentColor = colors.text.onBg,
            )
            else -> MoiveButtonStyle(
                containerColor = colors.primary.default,
                contentColor = colors.text.onBg,
            )
        }

        SECONDARY -> when {
            !enabled -> MoiveButtonStyle(
                containerColor = colors.fill.default06,
                contentColor = colors.text.disabled,
            )
            isPressed -> MoiveButtonStyle(
                containerColor = colors.fill.default02,
                contentColor = colors.text.onBg,
            )
            else -> MoiveButtonStyle(
                containerColor = colors.fill.default01,
                contentColor = colors.text.onBg,
            )
        }

        TERTIARY -> when {
            !enabled -> MoiveButtonStyle(
                containerColor = colors.fill.default08,
                contentColor = colors.text.disabled,
                borderColor = colors.stroke.default04,
            )
            isPressed -> MoiveButtonStyle(
                containerColor = colors.fill.default06,
                contentColor = colors.text.default,
                borderColor = colors.stroke.default03,
            )
            else -> MoiveButtonStyle(
                containerColor = colors.fill.default08,
                contentColor = colors.text.default,
                borderColor = colors.stroke.default03,
            )
        }
    }
}
