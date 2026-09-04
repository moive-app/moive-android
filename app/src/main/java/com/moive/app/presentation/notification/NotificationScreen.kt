package com.moive.app.presentation.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme

private val notifications = listOf(
    "모임 '모임1'의 조건 입력이 마감됐어요",
    "모임 '모임2'에서 투표가 시작됐어요",
    "모임 '모임3'의 장소가 확정됐어요",
)

@Composable
fun NotificationRoute(
    navigateToMeetingDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NotificationScreen(
        onNotificationItemClick = navigateToMeetingDetail,
        modifier = modifier,
    )
}

@Composable
private fun NotificationScreen(
    onNotificationItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "알림")

        notifications.forEach { notification ->
            Text(
                text = notification,
                modifier = Modifier
                    .clickable(onClick = onNotificationItemClick)
                    .padding(vertical = 12.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationScreenPreview() {
    MoiveTheme {
        NotificationScreen(
            onNotificationItemClick = {},
        )
    }
}
