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
    INFO;

    @Composable
    fun getStyle(): LabelStyle =
        when (this) {
            DATE ->
                LabelStyle(
                    contentColor = colors.gray800,
                    backgroundColor = colors.yellow,
                    borderColor = colors.yellow,
                )
            CONDITION ->
                LabelStyle(
                    contentColor = colors.pink400,
                    backgroundColor = colors.pink50,
                    borderColor = colors.pink100,
                )
            VOTING ->
                LabelStyle(
                    contentColor = colors.teal500,
                    backgroundColor = colors.teal50,
                    borderColor = colors.teal100,
                )
            CATEGORY ->
                LabelStyle(
                    contentColor = colors.blue500,
                    backgroundColor = colors.blue50,
                    borderColor = colors.blue50,
                )
            INFO ->
                LabelStyle(
                    contentColor = colors.purple500,
                    backgroundColor = colors.purple50,
                    borderColor = colors.purple50,
                )
            CONFIRMED ->
                LabelStyle(
                    contentColor = colors.purple500,
                    backgroundColor = colors.purple50,
                    borderColor = colors.purple200,
                )
            COMPLETE ->
                LabelStyle(
                    contentColor = colors.gray500,
                    backgroundColor = colors.gray50,
                    borderColor = colors.gray200,
                )
        }
}
