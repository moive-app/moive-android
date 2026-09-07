package com.moive.app.presentation.meeting.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.dialog.MoiveDialog
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun LeaveMeetingDialog(
    meetingName: String,
    onDismissRequest: () -> Unit,
    onLeaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MoiveDialog(
        title = "모임 나가기",
        description = "[${meetingName}] 모임을 정말 나가시겠습니까?",
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MoiveButton(
                text = "취소",
                onClick = onDismissRequest,
                type = MoiveButtonType.TERTIARY,
                size = MoiveButtonSize.MEDIUM,
                modifier = Modifier.weight(1f),
            )
            MoiveButton(
                text = "나가기",
                onClick = onLeaveClick,
                type = MoiveButtonType.PRIMARY,
                size = MoiveButtonSize.MEDIUM,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LeaveMeetingDialogPreview() {
    MoiveTheme {
        LeaveMeetingDialog(
            meetingName = "모임명",
            onDismissRequest = {},
            onLeaveClick = {},
        )
    }
}
