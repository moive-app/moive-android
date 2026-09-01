package com.moive.app.presentation.meeting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun MeetingRoute(
    modifier: Modifier = Modifier,
) {
    MeetingScreen(
        modifier = modifier,
    )
}

@Composable
private fun MeetingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Meeting Screen")
    }
}


@Preview(showBackground = true)
@Composable
private fun MeetingScreenPreview() {
    MoiveTheme {
        MeetingScreen()
    }
}
