package com.moive.app.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.toast.LocalToastTrigger
import com.moive.app.core.designsystem.component.toast.ToastType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.login.KakaoLoginManager.KakaoLoginResult
import com.moive.app.presentation.login.LoginContract.SideEffect.NavigateToHome
import com.moive.app.presentation.login.LoginContract.SideEffect.NavigateToSignUpComplete
import com.moive.app.presentation.login.LoginContract.SideEffect.OnShowToast
import com.moive.app.presentation.login.component.AgreementBottomSheet
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateToSignUpComplete: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val kakaoLoginManager = KakaoLoginManager()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lifeCycleOwner = LocalLifecycleOwner.current
    val showToast = LocalToastTrigger.current

    LaunchedEffect(lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    NavigateToHome -> navigateToHome()
                    NavigateToSignUpComplete -> navigateToSignUpComplete()
                    is OnShowToast -> showToast.invoke(sideEffect.msg, sideEffect.type)
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
                    is KakaoLoginResult.Failure -> viewModel.showToast(result.error, ToastType.ERROR)
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.background.default00
            )
            .padding(bottom = 12.dp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(140f))

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(128.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "함께 만나는 과정을\n더 간편하게",
            color = colors.text.default,
            style = typography.title.xlB,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(296f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colors.kakao.container,
                    shape = RoundedCornerShape(radius.md),
                )
                .noRippleClickable(onClick = onKakaoClick)
                .padding(vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_logo_kakao_20),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "카카오 로그인",
                color = colors.kakao.label,
                style = typography.label.lgSb,
            )
        }
    }

    if (uiState.needRegister) {
        AgreementBottomSheet(
            isServiceAgreed = uiState.isServiceAgreed,
            isPrivacyAgreed = uiState.isPrivacyAgreed,
            isMarketingAgreed = uiState.isMarketingAgreed,
            isConfirmEnabled = uiState.isBtnEnabled,
            onServiceClick = onServiceCheck,
            onPrivacyClick = onPrivacyCheck,
            onMarketingClick = onMarketingCheck,
            onConfirmClick = onConfirmClick,
            onDismissRequest = {},
        )
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
