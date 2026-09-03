package com.moive.app.presentation.meeting.infoconfirm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun MeetingInfoConfirmRoute(
    navigateToMeetingDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MeetingInfoConfirmScreen(
        onConfirmButtonClick = navigateToMeetingDetail,
        modifier = modifier,
    )
}

@Composable
private fun MeetingInfoConfirmScreen(
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "모임 정보 확인 화면")

        Button(
            onClick = onConfirmButtonClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = "확인")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MeetingInfoConfirmScreenPreview() {
    MoiveTheme {
        MeetingInfoConfirmScreen(
            onConfirmButtonClick = {},
        )
    }
}
