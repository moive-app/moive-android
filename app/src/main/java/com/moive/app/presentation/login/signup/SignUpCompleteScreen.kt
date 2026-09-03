package com.moive.app.presentation.login.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun SignUpCompleteRoute(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpCompleteScreen(
        onStartClick = navigateToHome,
        modifier = modifier,
    )
}

@Composable
private fun SignUpCompleteScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = onStartClick,
        ) {
            Text(text = "회원가입 완료. 모이브 시작하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpCompleteScreenPreview() {
    MoiveTheme {
        SignUpCompleteScreen(
            onStartClick = {},
        )
    }
}
