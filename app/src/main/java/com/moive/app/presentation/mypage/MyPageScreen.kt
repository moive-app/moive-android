package com.moive.app.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.core.designsystem.component.toast.LocalToastTrigger
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.NavigateToLogin
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.OnShowToast

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
    isLogoutConfirmVisible: Boolean,
    onLogoutClick: () -> Unit,
    onLogoutCancelClick: () -> Unit,
    onLogoutConfirmClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
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
    }
}


@Preview(showBackground = true, name = "기본")
@Composable
private fun MyPageScreenPreview() {
    MoiveTheme {
        MyPageScreen(
            isLogoutConfirmVisible = false,
            onLogoutClick = {},
            onLogoutCancelClick = {},
            onLogoutConfirmClick = {},
            onWithdrawClick = {},
        )
    }
}

@Preview(showBackground = true, name = "로그아웃 확인")
@Composable
private fun MyPageScreenLogoutConfirmPreview() {
    MoiveTheme {
        MyPageScreen(
            isLogoutConfirmVisible = true,
            onLogoutClick = {},
            onLogoutCancelClick = {},
            onLogoutConfirmClick = {},
            onWithdrawClick = {},
        )
    }
}
