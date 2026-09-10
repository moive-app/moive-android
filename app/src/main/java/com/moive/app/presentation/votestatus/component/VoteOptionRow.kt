package com.moive.app.presentation.votestatus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun VoteOptionRow(
    text: String,
    voteRatio: Float,
    isTopVote: Boolean,
    isVotedByMe: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(radius.md),
            )
            .background(
                color = if (isTopVote) colors.primary.sub03 else colors.background.default02
            )
            .clickable(onClick = onClick),
    ) {

        Box(
            modifier = Modifier
                .matchParentSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = voteRatio.coerceIn(0f, 1f))
                    .background(
                        color = if (!isTopVote) colors.background.default04 else colors.primary.default,
                        shape = RoundedCornerShape(radius.md),
                    ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                color = if (isTopVote) colors.text.onBg else colors.text.tertiary,
                style = typography.label.smM,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )

            if (isVotedByMe) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_line_pressed_20),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VoteOptionRowPreview() {
    MoiveTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            VoteOptionRow(
                text = "성수동 카페",
                voteRatio = 0.25f,
                isTopVote = false,
                isVotedByMe = false,
                onClick = {},
            )
            VoteOptionRow(
                text = "역삼동 이자카야",
                voteRatio = 0.25f,
                isTopVote = false,
                isVotedByMe = true,
                onClick = {},
            )
            VoteOptionRow(text = "역삼동 카페", voteRatio = 0.5f, isTopVote = true, isVotedByMe = true, onClick = {})
        }
    }
}
