package com.moive.app.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.extensions.customShadow

@Composable
fun ShadowButton(
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showShadow: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (showShadow) {
                    Modifier.customShadow(
                        shape = RoundedCornerShape(
                            topStart = radius.md,
                            topEnd = radius.md,
                        ),
                        color = colors.shadowBlack8,
                        blur = 12.dp,
                    )
                } else {
                    Modifier
                }
            )
            .background(
                color = colors.background.default00,
                shape = RoundedCornerShape(
                    topStart = radius.md,
                    topEnd = radius.md,
                ),
            )
    ) {
        MoiveButton(
            text = text,
            size = MoiveButtonSize.LARGE,
            type = MoiveButtonType.PRIMARY,
            onClick = onClick,
            enabled = isEnabled,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp),
        )
    }

}
