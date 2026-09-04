package com.moive.app.core.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.moive.app.R

object PretendardFont {
    val Regular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))
    val Medium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
    val SemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
    val Bold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
}

sealed interface TypographyTokens {
    @Immutable
    data class Title(
        val xlB: TextStyle,
        val lgB: TextStyle,
        val mdSb: TextStyle,
        val smSb: TextStyle,
        val xsSb: TextStyle,
    ) : TypographyTokens

    @Immutable
    data class Body(
        val mdNormalR: TextStyle,
        val mdReadingR: TextStyle,
        val smNormalR: TextStyle,
        val smReadingR: TextStyle,
    ) : TypographyTokens

    @Immutable
    data class Label(
        val lgSb: TextStyle,
        val mdSb: TextStyle,
        val mdM: TextStyle,
        val mdR: TextStyle,
        val smM: TextStyle,
        val smR: TextStyle,
        val xsM: TextStyle,
        val xsR: TextStyle,
        val xxsR: TextStyle,
    ) : TypographyTokens
}

@Immutable
data class MoiveTypography(
    val title: TypographyTokens.Title,
    val body: TypographyTokens.Body,
    val label: TypographyTokens.Label,
)


private fun moiveTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    letterSpacing: TextUnit = 0.em,
) = TextStyle(
    fontFamily = fontFamily,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    ),
)

val defaultMoiveTypography = MoiveTypography(
    title = TypographyTokens.Title(
        xlB = moiveTextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
        ),
        lgB = moiveTextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
        ),
        mdSb = moiveTextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
        ),
        smSb = moiveTextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
        xsSb = moiveTextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
    ),
    body = TypographyTokens.Body(
        mdNormalR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        mdReadingR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 16.sp,
            lineHeight = 28.sp,
        ),
        smNormalR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        smReadingR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 14.sp,
            lineHeight = 24.sp,
        ),
    ),
    label = TypographyTokens.Label(
        lgSb = moiveTextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
        ),
        mdSb = moiveTextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        mdM = moiveTextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        mdR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        ),
        smM = moiveTextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        smR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        xsM = moiveTextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        ),
        xsR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        ),
        xxsR = moiveTextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 11.sp,
            lineHeight = 16.sp,
        ),
    ),
)

@Preview(showBackground = true)
@Composable
fun MoiveTypographyPreview() {
    MoiveTheme {
        Column {
            Text("title.xlB", style = MoiveTheme.typography.title.xlB)
            Text("title.lgB", style = MoiveTheme.typography.title.lgB)
            Text("title.mdSb", style = MoiveTheme.typography.title.mdSb)
            Text("title.smSb", style = MoiveTheme.typography.title.smSb)
            Text("title.xsSb", style = MoiveTheme.typography.title.xsSb)

            Spacer(modifier = Modifier.height(12.dp))

            Text("body.mdNormalR", style = MoiveTheme.typography.body.mdNormalR)
            Text("body.mdReadingR", style = MoiveTheme.typography.body.mdReadingR)
            Text("body.smNormalR", style = MoiveTheme.typography.body.smNormalR)
            Text("body.smReadingR", style = MoiveTheme.typography.body.smReadingR)

            Spacer(modifier = Modifier.height(12.dp))

            Text("label.lgSb", style = MoiveTheme.typography.label.lgSb)
            Text("label.mdSb", style = MoiveTheme.typography.label.mdSb)
            Text("label.mdM", style = MoiveTheme.typography.label.mdM)
            Text("label.mdR", style = MoiveTheme.typography.label.mdR)
            Text("label.smM", style = MoiveTheme.typography.label.smM)
            Text("label.smR", style = MoiveTheme.typography.label.smR)
            Text("label.xsM", style = MoiveTheme.typography.label.xsM)
            Text("label.xsR", style = MoiveTheme.typography.label.xsR)
            Text("label.xxsR", style = MoiveTheme.typography.label.xxsR)
        }
    }
}
