package com.moive.app.presentation.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.core.designsystem.component.toast.LocalToastTrigger
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.NavigateToLogin
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.OnShowToast

@Composable
fun MyPageRoute(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val showToast = LocalToastTrigger.current

    LaunchedEffect(lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    NavigateToLogin -> navigateToLogin()
                    is OnShowToast -> showToast.invoke(sideEffect.msg)
                }
            }
        }
    }

    MyPageScreen(
        onLogoutClick = viewModel::postLogout,
        modifier = modifier,
    )
}

@Composable
private fun MyPageScreen(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "MyPage Screen")
        Text(
            text = "로그아웃",
            modifier = Modifier.noRippleClickable(onClick = onLogoutClick),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    MoiveTheme {
        MyPageScreen(
            onLogoutClick = {},
        )
    }
}
