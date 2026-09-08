package com.moive.app.presentation.notification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun NotificationSettingButton(
    isNotificationPermissionGranted: Boolean,
    onSettingClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.fill.default08,
                shape = RoundedCornerShape(radius.xl),
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_info_20),
            contentDescription = null,
            tint = colors.icon.default,
            modifier = Modifier.align(Alignment.Top)
        )

        Text(
            text = if (isNotificationPermissionGranted) {
                "알림 설정 바로가기"
            } else {
                "알림 받기를 설정하고 MOIVE의 알림을 받아보세요."
            },
            color = colors.text.secondary,
            style = typography.body.smNormalR,
            modifier = Modifier.weight(1f),
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_right_md_24),
            contentDescription = null,
            tint = colors.icon.tertiary,
            modifier = Modifier.noRippleClickable(onClick = onSettingClick),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationSettingButtonPreview() {
    MoiveTheme {
        NotificationSettingButton(
            isNotificationPermissionGranted = false,
            onSettingClick = {},
        )
    }
}
