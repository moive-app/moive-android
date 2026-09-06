package com.moive.app.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.toast.LocalToastTrigger
import com.moive.app.core.designsystem.component.topbar.MoiveSubIconTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.NavigateToLogin
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.OnShowToast
import com.moive.app.presentation.mypage.component.MyPageInfoCard
import com.moive.app.presentation.mypage.component.MyPageMenuItem
import com.moive.app.presentation.mypage.component.ProfileCard

@Composable
fun MyPageRoute(
    navigateToLogin: () -> Unit,
    navigateToWithDraw: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val showToast = LocalToastTrigger.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    NavigateToLogin -> navigateToLogin()
                    is OnShowToast -> showToast.invoke(sideEffect.msg, sideEffect.type)
                }
            }
        }
    }

    MyPageScreen(
        uiState = uiState,
        onAlarmClick = {},
        onQuestionClick = {},
        onTermsClick = {},
        isLogoutConfirmVisible = uiState.isLogoutConfirmVisible,
        onLogoutClick = viewModel::onLogoutClick,
        onLogoutCancelClick = viewModel::onLogoutCancelClick,
        onLogoutConfirmClick = viewModel::onLogoutConfirmClick,
        onWithdrawClick = navigateToWithDraw,
        modifier = modifier,
    )
}

@Composable
private fun MyPageScreen(
    uiState: MyPageContract.State,
    onAlarmClick: () -> Unit,
    onQuestionClick: () -> Unit,
    onTermsClick: () -> Unit,
    isLogoutConfirmVisible: Boolean,
    onLogoutClick: () -> Unit,
    onLogoutCancelClick: () -> Unit,
    onLogoutConfirmClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.background.default02
            ),
    ) {
        MoiveSubIconTopBar(
            leadingIcon = null,
            trailingIcon = if (uiState.hasUnReadAlarm) R.drawable.ic_bell_notification_24 else R.drawable.ic_bell_24,
            onTrailingIconClick = onAlarmClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileCard(
            profileImage = uiState.profileImage,
            name = uiState.name,
            email = uiState.email,
        )

        Spacer(modifier = Modifier.height(48.dp))

        MyPageInfoCard {
            Column {
                Text(
                    text = "고객 지원",
                    color = colors.text.subtle,
                    style = typography.label.xsM
                )

                Spacer(modifier = Modifier.height(6.dp))

                MyPageMenuItem(
                    text = "자주 묻는 질문",
                    onMoreClick = onQuestionClick,
                    modifier = Modifier.padding(vertical = 12.dp),
                )

                MyPageMenuItem(
                    text = "이용 약관",
                    onMoreClick = onTermsClick,
                    modifier = Modifier.padding(vertical = 12.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        MyPageInfoCard {
            MyPageMenuItem(
                text = "로그아웃",
                onMoreClick = onLogoutClick,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        MyPageInfoCard {
            MyPageMenuItem(
                text = "회원탈퇴",
                onMoreClick = onWithdrawClick,
            )
        }
    }
    /*Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "MyPage Screen")
            Text(
                text = "로그아웃",
                modifier = Modifier.noRippleClickable(onClick = onLogoutClick),
            )
            Text(
                text = "회원 탈퇴",
                modifier = Modifier.noRippleClickable(onClick = onWithdrawClick),
            )
        }

        if (isLogoutConfirmVisible) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(text = "로그아웃 하시겠어요?")

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Button(onClick = onLogoutConfirmClick) {
                            Text(text = "로그아웃")
                        }
                        OutlinedButton(onClick = onLogoutCancelClick) {
                            Text(text = "취소")
                        }
                    }
                }
            }
        }
    }*/
}


@Preview(showBackground = true, name = "기본")
@Composable
private fun MyPageScreenPreview() {
    MoiveTheme {
        MyPageScreen(
            uiState = MyPageContract.State(
                name = "다인다인",
                email = "dain@example.com"
            ),
            onAlarmClick = {},
            onQuestionClick = {},
            onTermsClick = {},
            isLogoutConfirmVisible = false,
            onLogoutClick = {},
            onLogoutCancelClick = {},
            onLogoutConfirmClick = {},
            onWithdrawClick = {},
        )
    }
}

