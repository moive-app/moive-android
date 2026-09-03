package com.moive.app.presentation.meeting.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MeetingListRoute(
    navigateToMeetingDetail: () -> Unit,
    navigateToMeetingComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MeetingListScreen(
        onOngoingMeetingClick = navigateToMeetingDetail,
        onEndMeetingClick = navigateToMeetingComplete,
        modifier = modifier,
    )
}

@Composable
private fun MeetingListScreen(
    onOngoingMeetingClick: () -> Unit,
    onEndMeetingClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "내 모임")

        MeetingCard(
            title = "진행중인 모임",
            onClick = onOngoingMeetingClick,
        )

        MeetingCard(
            title = "종료된 모임",
            onClick = onEndMeetingClick,
        )
    }
}

@Composable
private fun MeetingCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(16.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun MeetingListScreenPreview() {
    MoiveTheme {
        MeetingListScreen(
            onOngoingMeetingClick = {},
            onEndMeetingClick = {},
        )
    }
}
