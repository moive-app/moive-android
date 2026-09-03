package com.moive.app.presentation.votestatus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun VoteStatusRoute(
    modifier: Modifier = Modifier,
) {
    VoteStatusScreen(
        modifier = modifier,
    )
}

@Composable
private fun VoteStatusScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "투표 현황")
    }
}

@Preview(showBackground = true)
@Composable
private fun VoteStatusScreenPreview() {
    MoiveTheme {
        VoteStatusScreen()
    }
}
