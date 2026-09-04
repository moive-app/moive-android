package com.moive.app.presentation.withdraw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun WithDrawRoute(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    WithDrawScreen(
        onCancelClick = navigateBack,
        onWithdrawClick = navigateToLogin,
        modifier = modifier,
    )
}

@Composable
private fun WithDrawScreen(
    onCancelClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = "정말 탈퇴하시겠어요?",
            modifier = Modifier.align(Alignment.Center),
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // TODO: 회원 탈퇴 API 연동
            Button(
                onClick = onWithdrawClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "회원 탈퇴")
            }
            OutlinedButton(
                onClick = onCancelClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "취소")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WithDrawScreenPreview() {
    MoiveTheme {
        WithDrawScreen(
            onCancelClick = {},
            onWithdrawClick = {},
        )
    }
}
