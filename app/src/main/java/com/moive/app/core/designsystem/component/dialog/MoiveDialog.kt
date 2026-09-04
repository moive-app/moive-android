package com.moive.app.core.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.customShadow

@Composable
fun MoiveDialog(
    title: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    buttons: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        DialogContent(
            title = title,
            description = description,
            modifier = modifier,
            buttons = buttons,
        )
    }
}

@Composable
private fun DialogContent(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    buttons: @Composable () -> Unit,
) {
    val shape = RoundedCornerShape(radius.xl)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                shape = shape,
                color = colors.modalShadow,
                offsetY = 16.dp,
                blur = 24.dp,
                spread = (-6).dp,
            )
            .customShadow(
                shape = shape,
                color = colors.modalShadow,
                offsetY = 6.dp,
                blur = 10.dp,
                spread = (-4).dp,
            )
            .background(
                color = colors.fill.default08,
                shape = shape,
            )
            .padding(20.dp),
    ) {
        Text(
            text = title,
            color = colors.text.default,
            style = typography.title.lgB,
            modifier = Modifier.align(
                if (description == null)
                    Alignment.CenterHorizontally
                else
                    Alignment.Start
            )
        )

        if (description != null) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                color = colors.text.secondary,
                style = typography.label.mdR,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        buttons()
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
private fun MoiveDialogPreview() {
    MoiveTheme {
        var isModal1Visible by remember { mutableStateOf(false) }
        var isModal3Visible by remember { mutableStateOf(false) }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { isModal1Visible = true }) {
                Text(text = "모달 열기 (모임명)")
            }
            Button(onClick = { isModal3Visible = true }) {
                Text(text = "모달 열기 (나가기)")
            }
        }

        if (isModal1Visible) {
            MoiveDialog(
                title = "모임 나가기",
                description = "[모임명] 모임을 정말 나가시겠습니까?",
                onDismissRequest = { isModal1Visible = false },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MoiveButton(
                        text = "취소",
                        onClick = { isModal1Visible = false },
                        type = MoiveButtonType.WHITE,
                        size = MoiveButtonSize.MEDIUM,
                        modifier = Modifier.weight(1f)
                    )
                    MoiveButton(
                        text = "나가기",
                        onClick = { isModal1Visible = false },
                        type = MoiveButtonType.PURPLE,
                        size = MoiveButtonSize.MEDIUM,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        if (isModal3Visible) {
            MoiveDialog(
                title = "저장하지 않고 나가시겠습니까?",
                onDismissRequest = { isModal3Visible = false },
            ) {
                MoiveButton(
                    text = "닫기",
                    onClick = { isModal3Visible = false },
                    type = MoiveButtonType.PURPLE,
                    size = MoiveButtonSize.MEDIUM,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
