package com.moive.app.presentation.meeting.create

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
fun MeetingCreationRoute(
    navigateToMeetingInfoConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MeetingCreationScreen(
        onNextButtonClick = navigateToMeetingInfoConfirm,
        modifier = modifier,
    )
}

@Composable
private fun MeetingCreationScreen(
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "모임 생성")

        Button(
            onClick = onNextButtonClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = "다음")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MeetingCreationScreenPreview() {
    MoiveTheme {
        MeetingCreationScreen(
            onNextButtonClick = {},
        )
    }
}
