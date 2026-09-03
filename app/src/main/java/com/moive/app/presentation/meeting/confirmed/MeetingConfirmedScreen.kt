package com.moive.app.presentation.meeting.confirmed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun MeetingConfirmedRoute(
    modifier: Modifier = Modifier,
) {
    MeetingConfirmedScreen(
        modifier = modifier,
    )
}

@Composable
private fun MeetingConfirmedScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "확정된 모임")

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "친구에게 알려주기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingConfirmedScreenPreview() {
    MoiveTheme {
        MeetingConfirmedScreen()
    }
}
