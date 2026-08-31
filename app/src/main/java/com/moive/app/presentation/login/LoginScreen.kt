package com.moive.app.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.login.KakaoLoginManager.KakaoLoginResult
import com.moive.app.presentation.login.LoginContract.SideEffect.NavigateToMyPage
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val kakaoLoginManager = KakaoLoginManager()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lifeCycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    NavigateToMyPage -> navigateToMyPage()
                }
            }
        }
    }

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
        onServiceCheck = viewModel::onServiceCheck,
        onPrivacyCheck = viewModel::onPrivacyCheck,
        onMarketingCheck = viewModel::onMarketingCheck,
        onConfirmClick = viewModel::postSignUp,
        modifier = modifier,
    )
}

@Composable
private fun LoginScreen(
    uiState: LoginContract.State,
    onKakaoClick: () -> Unit,
    onServiceCheck: (Boolean) -> Unit,
    onPrivacyCheck: (Boolean) -> Unit,
    onMarketingCheck: (Boolean) -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (!uiState.needRegister) {
            Text(
                text = "Login Screen",
                modifier = Modifier.noRippleClickable(onClick = onKakaoClick)
            )
        } else {
            AgreementSection(
                isServiceAgreed = uiState.isServiceAgreed,
                isPrivacyAgreed = uiState.isPrivacyAgreed,
                isMarketingAgreed = uiState.isMarketingAgreed,
                isBtnEnabled = uiState.isBtnEnabled,
                onServiceCheck = onServiceCheck,
                onPrivacyCheck = onPrivacyCheck,
                onMarketingCheck = onMarketingCheck,
                onConfirmClick = onConfirmClick,
            )
        }
    }
}

@Composable
private fun AgreementSection(
    isServiceAgreed: Boolean,
    isPrivacyAgreed: Boolean,
    isMarketingAgreed: Boolean,
    isBtnEnabled: Boolean,
    onServiceCheck: (Boolean) -> Unit,
    onPrivacyCheck: (Boolean) -> Unit,
    onMarketingCheck: (Boolean) -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            Text(
                text = "이용 약관 동의   "
            )
            Checkbox(
                checked = isServiceAgreed,
                onCheckedChange = onServiceCheck,
            )
        }
        Row {
            Text(
                text = "개인정보 처리 방침   "
            )
            Checkbox(
                checked = isPrivacyAgreed,
                onCheckedChange = onPrivacyCheck,
            )
        }
        Row {
            Text(
                text = "마케팅 수신 동의   "
            )
            Checkbox(
                checked = isMarketingAgreed,
                onCheckedChange = onMarketingCheck,
            )
        }

        Button(
            onClick = onConfirmClick,
            enabled = isBtnEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MoiveTheme.colors.purple500,
                contentColor = MoiveTheme.colors.gray01,
                disabledContainerColor = MoiveTheme.colors.gray300,
                disabledContentColor = MoiveTheme.colors.gray01,
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "확인")
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
            onServiceCheck = {},
            onPrivacyCheck = {},
            onMarketingCheck = {},
            onConfirmClick = {},
        )
    }
}
