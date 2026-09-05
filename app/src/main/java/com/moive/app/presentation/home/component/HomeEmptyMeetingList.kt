package com.moive.app.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun HomeEmptyMeetingList(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colors.stroke.default04,
                shape = RoundedCornerShape(radius.xxl),
            )
            .background(
                color = colors.background.default01,
                shape = RoundedCornerShape(radius.xxl)
            )
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(
                    shape = RoundedCornerShape(radius.md),
                ),
        )

        Text(
            text = "아직 참여 중인 모임이 없어요.",
            color = colors.text.subtle,
            style = typography.body.smNormalR,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeEmptyMeetingListPreview() {
    MoiveTheme {
        HomeEmptyMeetingList(
            modifier = Modifier.padding(20.dp),
        )
    }
}
