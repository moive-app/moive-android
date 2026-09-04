package com.moive.app.core.designsystem.component.chip;

import androidx.compose.runtime.Composable;
import androidx.compose.ui.graphics.Color
import com.moive.app.core.designsystem.theme.MoiveTheme.colors

data class LabelStyle(
    val contentColor: Color,
    val backgroundColor: Color,
    val borderColor: Color,
)

enum class LabelType {
    DATE,
    CONDITION,
    VOTING,
    CONFIRMED,
    COMPLETE,
    CATEGORY,
    INFO,
    REGION;

    @Composable
    fun getStyle(): LabelStyle =
        when (this) {
            DATE ->
                LabelStyle(
                    contentColor = colors.text.default,
                    backgroundColor = colors.accent.yellow.default,
                    borderColor = colors.accent.yellow.default,
                )
            CONDITION ->
                LabelStyle(
                    contentColor = colors.accent.pink.default,
                    backgroundColor = colors.accent.pink.sub02,
                    borderColor = colors.accent.pink.sub01,
                )
            VOTING ->
                LabelStyle(
                    contentColor = colors.accent.teal.default,
                    backgroundColor = colors.accent.teal.sub02,
                    borderColor = colors.accent.teal.sub01,
                )
            CATEGORY ->
                LabelStyle(
                    contentColor = colors.secondary.pressed,
                    backgroundColor = colors.secondary.sub03,
                    borderColor = colors.secondary.sub03,
                )
            INFO ->
                LabelStyle(
                    contentColor = colors.primary.default,
                    backgroundColor = colors.primary.sub03,
                    borderColor = colors.primary.sub03,
                )
            CONFIRMED ->
                LabelStyle(
                    contentColor = colors.primary.default,
                    backgroundColor = colors.primary.sub03,
                    borderColor = colors.primary.sub02,
                )
            COMPLETE ->
                LabelStyle(
                    contentColor = colors.text.tertiary,
                    backgroundColor = colors.fill.default06,
                    borderColor = colors.stroke.default03,
                )
            REGION ->
                LabelStyle(
                    contentColor = colors.text.onBg,
                    backgroundColor = colors.fill.default01,
                    borderColor = colors.fill.default01,
                )
        }
}
