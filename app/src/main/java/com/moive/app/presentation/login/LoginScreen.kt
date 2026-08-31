package com.moive.app.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.login.KakaoLoginManager.KakaoLoginResult
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val kakaoLoginManager = KakaoLoginManager()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LoginScreen(
        uiState = uiState,
        onKakaoClick = {
            scope.launch {
                when (val result = kakaoLoginManager.loginKakao(context)) {
                    is KakaoLoginResult.Success -> viewModel.postKakaoLogin(result.token)
                    is KakaoLoginResult.Failure -> viewModel.showToast(result.error)
                }
            }
        },
        onCompleteClick = {},
        modifier = modifier,
    )
}

@Composable
private fun LoginScreen(
    uiState: LoginContract.State,
    onKakaoClick: () -> Unit,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if(!uiState.isLoginComplete){
            Text(
                text = "Login Screen",
                modifier = Modifier.noRippleClickable(onClick = onKakaoClick)
            )
        } else {
            Text(
                text = "로그인 완료",
                modifier = Modifier.noRippleClickable(onClick = onCompleteClick)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MoiveTheme {
        LoginScreen(
            uiState = LoginContract.State(),
            onKakaoClick = {},
            onCompleteClick = {},
        )
    }
}
