package com.moive.app.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Purple
val Purple50 = Color(0xFFEDE8FF)
val Purple200 = Color(0xFFCBC5FF)
val Purple300 = Color(0xFFB3AAFF)
val Purple500 = Color(0xFF7742FE)
val Purple700 = Color(0xFF542FB4)

// Blue
val Blue50 = Color(0xFFE9EEFE)
val Blue200 = Color(0xFF9BB3FA)
val Blue300 = Color(0xFF6D90F8)
val Blue400 = Color(0xFF517AF6)
val Blue500 = Color(0xFF3661E0)

// Pink
val Pink50 = Color(0xFFFAECF3)
val Pink100 = Color(0xFFE9B2CD)
val Pink400 = Color(0xFFCA4485)

// Teal
val Teal50 = Color(0xFFE0F5FA)
val Teal100 = Color(0xFF9DD4D5)
val Teal500 = Color(0xFF0B6A6B)

// Green
val Green50 = Color(0xFFE6F9EB)
val Green200 = Color(0xFF9EE6B5)
val Green500 = Color(0xFF00BF40)
val Green700 = Color(0xFF00892D)

// Orange
val Orange50 = Color(0xFFFFF5E8)
val Orange200 = Color(0xFFFFD08A)
val Orange500 = Color(0xFFFF9200)
val Orange700 = Color(0xFFBF5F00)

// Red
val Red50 = Color(0xFFFFF1F1)
val Red200 = Color(0xFFFFC2C2)
val Red500 = Color(0xFFFF4242)
val Red700 = Color(0xFFC82424)

// Yellow
val Yellow50 = Color(0xFFFFFBEA)
val Yellow100 = Color(0xFFFFF4BF)
val Yellow500 = Color(0xFFFFD728)

// Gray
val Gray01 = Color(0xFFFFFFFF)
val Gray10 = Color(0xFFFBFBFB)
val Gray50 = Color(0xFFF1F3F5)
val Gray100 = Color(0xFFE9ECEF)
val Gray200 = Color(0xFFD7DBDF)
val Gray300 = Color(0xFFBCC2C8)
val Gray400 = Color(0xFF8E959C)
val Gray500 = Color(0xFF6D747B)
val Gray600 = Color(0xFF495057)
val Gray800 = Color(0xFF212528)
val Gray900 = Color(0xFF16181A)

// Alpha
val WhiteAlpha40 = Color(0x66FFFFFF)

// Shadow
val Shadow8 = Color(0x1416181A)
val Shadow20 = Color(0x3316181A)

@Immutable
data class MoiveColors(
    val primary: Primary,
    val secondary: Secondary,
    val text: Text,
    val icon: Icon,
    val stroke: Stroke,
    val fill: Fill,
    val background: Background,
    val status: Status,
    val accent: Accent,
    val shadow8: Color,
    val shadow20: Color,
) {
    @Immutable
    data class Primary(
        val default: Color,
        val pressed: Color,
        val sub01: Color,
        val sub02: Color,
        val sub03: Color,
    )

    @Immutable
    data class Secondary(
        val default: Color,
        val pressed: Color,
        val sub01: Color,
        val sub02: Color,
        val sub03: Color,
    )

    @Immutable
    data class Text(
        val default: Color,
        val secondary: Color,
        val tertiary: Color,
        val subtle: Color,
        val disabled: Color,
        val onBg: Color,
    )

    @Immutable
    data class Icon(
        val default: Color,
        val secondary: Color,
        val tertiary: Color,
        val disabled: Color,
        val onBg: Color,
        val onBgSub: Color,
    )

    @Immutable
    data class Stroke(
        val default00: Color,
        val default01: Color,
        val default02: Color,
        val default03: Color,
        val default04: Color,
        val default05: Color,
        val onBg: Color,
    )

    @Immutable
    data class Fill(
        val default00: Color,
        val default01: Color,
        val default02: Color,
        val default03: Color,
        val default04: Color,
        val default05: Color,
        val default06: Color,
        val default07: Color,
        val default08: Color,
    )

    @Immutable
    data class Background(
        val default00: Color,
        val default01: Color,
        val default02: Color,
        val default03: Color,
        val default04: Color,
        val default05: Color,
    )

    @Immutable
    data class Status(
        val success: Success,
        val caution: Caution,
        val error: Error,
    ) {
        @Immutable
        data class Success(
            val default: Color,
            val pressed: Color,
            val sub01: Color,
            val sub02: Color,
        )

        @Immutable
        data class Caution(
            val default: Color,
            val pressed: Color,
            val sub01: Color,
            val sub02: Color,
        )

        @Immutable
        data class Error(
            val default: Color,
            val pressed: Color,
            val sub01: Color,
            val sub02: Color,
        )
    }

    @Immutable
    data class Accent(
        val yellow: Yellow,
        val pink: Pink,
        val teal: Teal,
    ) {
        @Immutable
        data class Yellow(
            val default: Color,
            val sub01: Color,
            val sub02: Color,
        )

        @Immutable
        data class Pink(
            val default: Color,
            val sub01: Color,
            val sub02: Color,
        )

        @Immutable
        data class Teal(
            val default: Color,
            val sub01: Color,
            val sub02: Color,
        )
    }
}

val defaultMoiveColors = MoiveColors(
    primary = MoiveColors.Primary(
        default = Purple500,
        pressed = Purple700,
        sub01 = Purple300,
        sub02 = Purple200,
        sub03 = Purple50,
    ),
    secondary = MoiveColors.Secondary(
        default = Blue400,
        pressed = Blue500,
        sub01 = Blue300,
        sub02 = Blue200,
        sub03 = Blue50,
    ),
    text = MoiveColors.Text(
        default = Gray800,
        secondary = Gray600,
        tertiary = Gray500,
        subtle = Gray400,
        disabled = Gray300,
        onBg = Gray01,
    ),
    icon = MoiveColors.Icon(
        default = Gray800,
        secondary = Gray600,
        tertiary = Gray500,
        disabled = Gray300,
        onBg = Gray01,
        onBgSub = WhiteAlpha40,
    ),
    stroke = MoiveColors.Stroke(
        default00 = Gray800,
        default01 = Gray500,
        default02 = Gray300,
        default03 = Gray200,
        default04 = Gray100,
        default05 = Gray50,
        onBg = Gray01,
    ),
    fill = MoiveColors.Fill(
        default00 = Gray900,
        default01 = Gray800,
        default02 = Gray600,
        default03 = Gray300,
        default04 = Gray200,
        default05 = Gray100,
        default06 = Gray50,
        default07 = Gray10,
        default08 = Gray01,
    ),
    background = MoiveColors.Background(
        default00 = Gray01,
        default01 = Gray10,
        default02 = Gray50,
        default03 = Gray100,
        default04 = Gray200,
        default05 = Gray300,
    ),
    status = MoiveColors.Status(
        success = MoiveColors.Status.Success(
            default = Green500,
            pressed = Green700,
            sub01 = Green200,
            sub02 = Green50,
        ),
        caution = MoiveColors.Status.Caution(
            default = Orange500,
            pressed = Orange700,
            sub01 = Orange200,
            sub02 = Orange50,
        ),
        error = MoiveColors.Status.Error(
            default = Red500,
            pressed = Red700,
            sub01 = Red200,
            sub02 = Red50,
        ),
    ),
    accent = MoiveColors.Accent(
        yellow = MoiveColors.Accent.Yellow(
            default = Yellow500,
            sub01 = Yellow100,
            sub02 = Yellow50,
        ),
        pink = MoiveColors.Accent.Pink(
            default = Pink400,
            sub01 = Pink100,
            sub02 = Pink50,
        ),
        teal = MoiveColors.Accent.Teal(
            default = Teal500,
            sub01 = Teal100,
            sub02 = Teal50,
        ),
    ),
    shadow8 = Shadow8,
    shadow20 = Shadow20,
)

@Preview
@Composable
private fun MoiveColorsPreview() {
    MoiveTheme {
        Column(modifier = Modifier.padding(8.dp)) {
            val colors = MoiveTheme.colors

            ColorRow("primary", listOf(colors.primary.default, colors.primary.pressed, colors.primary.sub01, colors.primary.sub02, colors.primary.sub03))
            ColorRow("secondary", listOf(colors.secondary.default, colors.secondary.pressed, colors.secondary.sub01, colors.secondary.sub02, colors.secondary.sub03))
            ColorRow("text", listOf(colors.text.default, colors.text.secondary, colors.text.tertiary, colors.text.subtle, colors.text.disabled, colors.text.onBg))
            ColorRow("icon", listOf(colors.icon.default, colors.icon.secondary, colors.icon.tertiary, colors.icon.disabled, colors.icon.onBg, colors.icon.onBgSub))
            ColorRow("stroke", listOf(colors.stroke.default00, colors.stroke.default01, colors.stroke.default02, colors.stroke.default03, colors.stroke.default04, colors.stroke.default05, colors.stroke.onBg))
            ColorRow("fill", listOf(colors.fill.default00, colors.fill.default01, colors.fill.default02, colors.fill.default03, colors.fill.default04, colors.fill.default05, colors.fill.default06, colors.fill.default07, colors.fill.default08))
            ColorRow("background", listOf(colors.background.default00, colors.background.default01, colors.background.default02, colors.background.default03, colors.background.default04, colors.background.default05))
            ColorRow("status.success", listOf(colors.status.success.default, colors.status.success.pressed, colors.status.success.sub01, colors.status.success.sub02))
            ColorRow("status.caution", listOf(colors.status.caution.default, colors.status.caution.pressed, colors.status.caution.sub01, colors.status.caution.sub02))
            ColorRow("status.error", listOf(colors.status.error.default, colors.status.error.pressed, colors.status.error.sub01, colors.status.error.sub02))
            ColorRow("accent.yellow", listOf(colors.accent.yellow.default, colors.accent.yellow.sub01, colors.accent.yellow.sub02))
            ColorRow("accent.pink", listOf(colors.accent.pink.default, colors.accent.pink.sub01, colors.accent.pink.sub02))
            ColorRow("accent.teal", listOf(colors.accent.teal.default, colors.accent.teal.sub01, colors.accent.teal.sub02))
        }
    }
}

@Composable
private fun ColorRow(label: String, swatches: List<Color>) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, modifier = Modifier.padding(end = 8.dp))
        swatches.forEach { color ->
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color),
            )
        }
    }
}
