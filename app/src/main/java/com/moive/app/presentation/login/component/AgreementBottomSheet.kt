package com.moive.app.presentation.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.bottomsheet.MoiveBottomSheet
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.core.extensions.openUrl

//Todo: 실제 URL로 수정
private const val SERVICE_TERMS_URL = "https://www.notion.so/서비스-이용약관"
private const val PRIVACY_POLICY_URL = "https://www.notion.so/개인정보-처리방침"
private const val MARKETING_URL = "https://www.notion.so/마케팅-수신동의"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgreementBottomSheet(
    isServiceAgreed: Boolean,
    isPrivacyAgreed: Boolean,
    isMarketingAgreed: Boolean,
    isConfirmEnabled: Boolean,
    onServiceClick: (Boolean) -> Unit,
    onPrivacyClick: (Boolean) -> Unit,
    onMarketingClick: (Boolean) -> Unit,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isAllAgreed = isServiceAgreed && isPrivacyAgreed && isMarketingAgreed

    MoiveBottomSheet(
        title = "약관동의",
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = {
            Column (
                modifier = Modifier.padding(top = 20.dp)
            ){
                AllAgreementButton(
                    isChecked = isAllAgreed,
                    onAllAgreementClick = { checked ->
                        onServiceClick(checked)
                        onPrivacyClick(checked)
                        onMarketingClick(checked)
                    },
                )

                Spacer(modifier = Modifier.height(8.dp))

                AgreementItem(
                    label = "(필수) 서비스 이용약관",
                    isChecked = isServiceAgreed,
                    onCheckedChange = onServiceClick,
                    detailUrl = SERVICE_TERMS_URL,
                )

                AgreementItem(
                    label = "(필수) 개인정보 처리방침",
                    isChecked = isPrivacyAgreed,
                    onCheckedChange = onPrivacyClick,
                    detailUrl = PRIVACY_POLICY_URL,
                )

                AgreementItem(
                    label = "(선택) 마케팅 수신동의",
                    isChecked = isMarketingAgreed,
                    onCheckedChange = onMarketingClick,
                    detailUrl = MARKETING_URL,
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        },
        buttonContent = {
            MoiveButton(
                text = "다음",
                type = MoiveButtonType.PRIMARY,
                size = MoiveButtonSize.LARGE,
                enabled = isConfirmEnabled,
                onClick = onConfirmClick,
            )
        },
    )
}

@Composable
private fun AllAgreementButton(
    isChecked: Boolean,
    onAllAgreementClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isPressed) Color.Transparent else colors.stroke.default05,
                shape = RoundedCornerShape(radius.md),
            )
            .background(
                color = if (isPressed) colors.fill.default06 else colors.fill.default07,
                shape = RoundedCornerShape(radius.md),
            )
            .noRippleClickable(onClick = { onAllAgreementClick(!isChecked) })
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CheckIcon(isChecked = isChecked)

        Text(
            text = "전체 동의",
            color = colors.text.default,
            style = typography.title.mdSb,
        )
    }
}

@Composable
private fun AgreementItem(
    label: String,
    isChecked: Boolean,
    detailUrl: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = { onCheckedChange(!isChecked) })
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CheckIcon(isChecked = isChecked)

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = label,
            color = colors.text.default,
            style = typography.body.mdNormalR,
            modifier = Modifier.weight(1f),
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_chevron_right_20),
            contentDescription = null,
            modifier = Modifier.noRippleClickable(
                onClick = { context.openUrl(detailUrl) }
            ),
        )
    }
}

@Composable
private fun CheckIcon(
    isChecked: Boolean
) {
    Icon(
        imageVector = ImageVector.vectorResource(
            if (isChecked) R.drawable.ic_check_pressed_20 else R.drawable.ic_check_disabled_20
        ),
        contentDescription = null,
        tint = Color.Unspecified,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AgreementBottomSheetPreview() {
    MoiveTheme {
        var showBottomSheet by remember { mutableStateOf(false) }
        var isServiceAgreed by remember { mutableStateOf(false) }
        var isPrivacyAgreed by remember { mutableStateOf(false) }
        var isMarketingAgreed by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MoiveButton(
                text = "약관동의 바텀시트 열기",
                type = MoiveButtonType.PRIMARY,
                size = MoiveButtonSize.LARGE,
                onClick = { showBottomSheet = true },
            )
        }

        if (showBottomSheet) {
            AgreementBottomSheet(
                isServiceAgreed = isServiceAgreed,
                isPrivacyAgreed = isPrivacyAgreed,
                isMarketingAgreed = isMarketingAgreed,
                isConfirmEnabled = isServiceAgreed && isPrivacyAgreed,
                onServiceClick = { isServiceAgreed = it },
                onPrivacyClick = { isPrivacyAgreed = it },
                onMarketingClick = { isMarketingAgreed = it },
                onConfirmClick = {},
                onDismissRequest = { showBottomSheet = false },
            )
        }
    }
}
