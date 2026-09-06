package com.moive.app.presentation.meeting.complete

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun MeetingCompleteRoute(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    MeetingCompleteScreen(
        innerPadding = innerPadding,
        modifier = modifier,
    )
}

@Composable
private fun MeetingCompleteScreen(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "종료된 모임")
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingCompleteScreenPreview() {
    MoiveTheme {
        MeetingCompleteScreen(innerPadding = PaddingValues())
    }
}
