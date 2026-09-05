package com.moive.app.presentation.login.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

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
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.background.default00
            )
            .padding(horizontal = 20.dp)
            .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(184f))

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(240.dp),
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "가입이 완료됐어요!",
            color = colors.text.default,
            style = typography.title.xlB
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "모이브에서 첫 모임을 시작해보세요.",
            color = colors.text.tertiary,
            style = typography.body.mdNormalR,
        )

        Spacer(modifier = Modifier.weight(144f))

        MoiveButton(
            text = "모이브 시작하기",
            type = MoiveButtonType.PRIMARY,
            size = MoiveButtonSize.LARGE,
            onClick = onStartClick,
        )
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
