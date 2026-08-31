package com.moive.app.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val kakaoLoginManager = KakaoLoginManager()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LoginScreen(
        onKakaoClick = {
            scope.launch {
                kakaoLoginManager.loginKakao(context)
                    .onSuccess { token ->
                        viewModel.postKakaoLogin(token)
                    }
                    .onFailure { error ->
                        val errorMessage = error.message ?: ""
                        viewModel.showToast(errorMessage)
                    }
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun LoginScreen(
    onKakaoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Login Screen",
            modifier = Modifier.noRippleClickable(onClick = onKakaoClick)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MoiveTheme {
        LoginScreen(
            onKakaoClick = {},
        )
    }
}
