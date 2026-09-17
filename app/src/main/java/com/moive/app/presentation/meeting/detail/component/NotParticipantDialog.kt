package com.moive.app.presentation.meeting.detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.dialog.MoiveDialog
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun NotParticipantDialog(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MoiveDialog(
        title = "탈퇴한 모임",
        description = "탈퇴한 모임이에요! 접근할 수 없어요.",
        onDismissRequest = onCloseClick,
        modifier = modifier,
    ) {
        MoiveButton(
            text = "닫기",
            onClick = onCloseClick,
            type = MoiveButtonType.PRIMARY,
            size = MoiveButtonSize.MEDIUM,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotParticipantDialogPreview() {
    MoiveTheme {
        NotParticipantDialog(
            onCloseClick = {},
        )
    }
}
