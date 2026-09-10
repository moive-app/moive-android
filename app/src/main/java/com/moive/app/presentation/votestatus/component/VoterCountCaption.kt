package com.moive.app.presentation.votestatus.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun VoterCountCaption(
    voterCount: Int?,
    suffix: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_tap_03_16),
            contentDescription = null,
            tint = colors.icon.secondary,
        )

        Text(
            text = buildAnnotatedString {
                if (voterCount != null) {
                    withStyle(SpanStyle(color = colors.primary.default)) {
                        append("${voterCount}명")
                    }
                }
                append(suffix)
            },
            color = colors.text.secondary,
            style = typography.label.xsR,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VoterCountCaptionPreview() {
    MoiveTheme {
        VoterCountCaption(voterCount = 4, suffix = "이 투표했어요")
    }
}
