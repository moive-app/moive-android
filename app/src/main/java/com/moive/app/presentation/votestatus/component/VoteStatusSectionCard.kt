package com.moive.app.presentation.votestatus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun VoteStatusSectionCard(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    voteContent: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = title,
            color = colors.text.default,
            style = typography.title.lgB,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colors.background.default00,
                    shape = RoundedCornerShape(radius.xl),
                )
                .padding(16.dp),
        ) {
            Text(
                text = subTitle,
                color = colors.text.default,
                style = typography.title.mdSb,
            )

            voteContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VoteStatusSectionCardPreview() {
    MoiveTheme {
        VoteStatusSectionCard(
            title = "일정 투표",
            subTitle = "언제 만날까요?"
        ) {

        }
    }
}
