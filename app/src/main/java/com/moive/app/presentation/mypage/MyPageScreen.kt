package com.moive.app.presentation.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MyPageRoute(
    modifier: Modifier = Modifier,
) {
    MyPageScreen(
        onLogoutClick = {},
        modifier = modifier,
    )
}

@Composable
private fun MyPageScreen(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "MyPage Screen")
        Text(
            text = "로그아웃",
            modifier = Modifier.noRippleClickable(onClick = onLogoutClick),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    MoiveTheme {
        MyPageScreen(
            onLogoutClick = {},
        )
    }
}
