package com.moive.app.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
) {
    LoginScreen(
        modifier = modifier,
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Login Screen")
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MoiveTheme {
        LoginScreen()
    }
}
