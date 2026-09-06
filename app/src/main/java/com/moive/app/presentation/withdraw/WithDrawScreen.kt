package com.moive.app.presentation.withdraw

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun WithDrawRoute(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WithDrawViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WithDrawScreen(
        uiState = uiState,
        onCancelClick = navigateBack,
        onAgreementClick = viewModel::toggleAgreement,
        onWithdrawClick = navigateToLogin,
        modifier = modifier,
    )
}

@Composable
private fun WithDrawScreen(
    uiState: WithDrawContract.State,
    onCancelClick: () -> Unit,
    onAgreementClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background.default00),
    ) {
        MoiveSubTitleTopBar(
            title = "회원 탈퇴",
            onBackClick = onCancelClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        WithDrawContent(
            isAgreed = uiState.isAgreed,
            onAgreementClick = onAgreementClick,
            modifier = Modifier.weight(1f),
        )


        MoiveButton(
            text = "회원 탈퇴",
            type = MoiveButtonType.PRIMARY,
            size = MoiveButtonSize.LARGE,
            enabled = uiState.isAgreed,
            onClick = onWithdrawClick,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
        )
    }
}

@Composable
private fun WithDrawContent(
    isAgreed: Boolean,
    onAgreementClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colors.status.error.sub01,
                    shape = RoundedCornerShape(radius.lg),
                )
                .background(
                    color = colors.status.error.sub02,
                    shape = RoundedCornerShape(radius.lg),
                )
                .padding(20.dp),
        ) {
            Text(
                text = "탈퇴 전 꼭 확인해 주세요.",
                color = colors.status.error.default,
                style = typography.title.smSb,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "• 탈퇴 시 모든 모임 기록과 데이터가 삭제됩니다.",
                color = colors.status.error.default,
                style = typography.label.mdR,
            )
            Text(
                text = "• 삭제된 데이터는 복구할 수 없어요.",
                color = colors.status.error.default,
                style = typography.label.mdR,
            )
            Text(
                text = "• 탈퇴 후 같은 계정으로 재가입 시 이전 데이터는 복원되지 않습니다.",
                color = colors.status.error.default,
                style = typography.label.mdR,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.noRippleClickable(onClick = onAgreementClick),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isAgreed) R.drawable.ic_check_pressed_20 else R.drawable.ic_check_disabled_20
                ),
                contentDescription = null,
                tint = Color.Unspecified,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "위 내용을 모두 확인하였으며, 탈퇴에 동의합니다.",
                color = colors.text.secondary,
                style = typography.body.smNormalR,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun WithDrawScreenPreview() {
    MoiveTheme {
        var uiState by remember { mutableStateOf(WithDrawContract.State()) }

        WithDrawScreen(
            uiState = uiState,
            onCancelClick = {},
            onAgreementClick = { uiState = uiState.copy(isAgreed = !uiState.isAgreed) },
            onWithdrawClick = {},
        )
    }
}
