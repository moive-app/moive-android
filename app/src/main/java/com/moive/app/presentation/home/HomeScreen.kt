package com.moive.app.presentation.home

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
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        modifier = modifier,
    )
}

@Composable
private fun HomeScreen(
    onShowListClick: () -> Unit,
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
        Text(
            text = "내모임 전체보기",
            modifier = Modifier.noRippleClickable(onClick = onShowListClick),
        )

        Text(
            text = "진행중인 모임",
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable(onClick = onOngoingMeetingClick)
                .padding(16.dp),
        )

        Text(
            text = "종료된 모임",
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable(onClick = onEndMeetingClick)
                .padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MoiveTheme {
        HomeScreen(
            onShowListClick = {},
            onOngoingMeetingClick = {},
            onEndMeetingClick = {},
        )
    }
}
