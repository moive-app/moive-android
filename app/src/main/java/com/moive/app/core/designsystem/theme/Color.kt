package com.moive.app.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Primary
val Purple50 = Color(0xFFF3F2FF)
val Purple100 = Color(0xFFE2DFFF)
val Purple200 = Color(0xFFC8C7F6)
val Purple300 = Color(0xFFB3AAFF)
val Purple400 = Color(0xFF9178FF)
val Purple500 = Color(0xFF7742FE)
val Purple600 = Color(0xFF6C3CE7)
val Purple700 = Color(0xFF542FB4)
val Purple800 = Color(0xFF41248C)
val Purple900 = Color(0xFF321C6B)

// Secondary
val Blue50 = Color(0xFFE9EEFE)
val Blue100 = Color(0xFFBBCCFC)
val Blue200 = Color(0xFF9BB3FA)
val Blue300 = Color(0xFF6D90F8)
val Blue400 = Color(0xFF517AF6)
val Blue500 = Color(0xFF3661E0)
val Blue600 = Color(0xFF2647CC)
val Blue700 = Color(0xFF203FAA)
val Blue800 = Color(0xFF1A3389)
val Blue900 = Color(0xFF14286C)

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
val Gray700 = Color(0xFF343A40)
val Gray800 = Color(0xFF212528)
val Gray900 = Color(0xFF16181A)

// Status
val Positive = Color(0xFF00BF40)
val Cautionary = Color(0xFFFF9200)
val Destructive = Color(0xFFFF4242)

// Accent
val Yellow = Color(0xFFFFD728)

@Immutable
data class MoiveColors(
    // Primary
    val purple50: Color,
    val purple100: Color,
    val purple200: Color,
    val purple300: Color,
    val purple400: Color,
    val purple500: Color,
    val purple600: Color,
    val purple700: Color,
    val purple800: Color,
    val purple900: Color,

    // Secondary
    val blue50: Color,
    val blue100: Color,
    val blue200: Color,
    val blue300: Color,
    val blue400: Color,
    val blue500: Color,
    val blue600: Color,
    val blue700: Color,
    val blue800: Color,
    val blue900: Color,

    // Gray
    val gray01: Color,
    val gray10: Color,
    val gray50: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray700: Color,
    val gray800: Color,
    val gray900: Color,

    // Status
    val positive: Color,
    val cautionary: Color,
    val destructive: Color,

    // Accent
    val yellow: Color,
)

val defaultMoiveColors = MoiveColors(
    // Primary
    purple50 = Purple50,
    purple100 = Purple100,
    purple200 = Purple200,
    purple300 = Purple300,
    purple400 = Purple400,
    purple500 = Purple500,
    purple600 = Purple600,
    purple700 = Purple700,
    purple800 = Purple800,
    purple900 = Purple900,

    // Secondary
    blue50 = Blue50,
    blue100 = Blue100,
    blue200 = Blue200,
    blue300 = Blue300,
    blue400 = Blue400,
    blue500 = Blue500,
    blue600 = Blue600,
    blue700 = Blue700,
    blue800 = Blue800,
    blue900 = Blue900,

    // Gray
    gray01 = Gray01,
    gray10 = Gray10,
    gray50 = Gray50,
    gray100 = Gray100,
    gray200 = Gray200,
    gray300 = Gray300,
    gray400 = Gray400,
    gray500 = Gray500,
    gray600 = Gray600,
    gray700 = Gray700,
    gray800 = Gray800,
    gray900 = Gray900,

    // Status
    positive = Positive,
    cautionary = Cautionary,
    destructive = Destructive,

    // Accent
    yellow = Yellow,
)

@Preview
@Composable
private fun MoiveColorsPreview() {
    MoiveTheme {
        Column {
            listOf(
                MoiveTheme.colors.purple50,
                MoiveTheme.colors.purple100,
                MoiveTheme.colors.purple200,
                MoiveTheme.colors.purple300,
                MoiveTheme.colors.purple400,
                MoiveTheme.colors.purple500,
                MoiveTheme.colors.purple600,
                MoiveTheme.colors.purple700,
                MoiveTheme.colors.purple800,
                MoiveTheme.colors.purple900,
                MoiveTheme.colors.blue50,
                MoiveTheme.colors.blue100,
                MoiveTheme.colors.blue200,
                MoiveTheme.colors.blue300,
                MoiveTheme.colors.blue400,
                MoiveTheme.colors.blue500,
                MoiveTheme.colors.blue600,
                MoiveTheme.colors.blue700,
                MoiveTheme.colors.blue800,
                MoiveTheme.colors.blue900,
                MoiveTheme.colors.gray01,
                MoiveTheme.colors.gray10,
                MoiveTheme.colors.gray50,
                MoiveTheme.colors.gray100,
                MoiveTheme.colors.gray200,
                MoiveTheme.colors.gray300,
                MoiveTheme.colors.gray400,
                MoiveTheme.colors.gray500,
                MoiveTheme.colors.gray600,
                MoiveTheme.colors.gray700,
                MoiveTheme.colors.gray800,
                MoiveTheme.colors.gray900,
                MoiveTheme.colors.positive,
                MoiveTheme.colors.cautionary,
                MoiveTheme.colors.destructive,
                MoiveTheme.colors.yellow,
            ).chunked(10).forEach { rowColors ->
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    rowColors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(color),
                        )
                    }
                }
            }
        }
    }
}
