package com.moive.app.presentation.condition.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.bottomsheet.MoiveBottomSheet
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(
    onDateBottomSheetDismiss: () -> Unit,
    onSaveDateClick: () -> Unit,
    onNextDateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MoiveBottomSheet(
        title = "일자 선택",
        modifier = modifier,
        onDismissRequest = onDateBottomSheetDismiss,
        content = {
            Text(
                text = "캘린더",
                color = colors.text.subtle,
                style = typography.body.smNormalR,
                modifier = Modifier.padding(vertical = 80.dp),
            )
        },
        buttonContent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                MoiveButton(
                    text = "저장",
                    type = MoiveButtonType.TERTIARY,
                    size = MoiveButtonSize.LARGE,
                    onClick = onSaveDateClick,
                    modifier = Modifier.weight(1f),
                )
                MoiveButton(
                    text = "다음",
                    type = MoiveButtonType.PRIMARY,
                    size = MoiveButtonSize.LARGE,
                    onClick = onNextDateClick,
                    modifier = Modifier.weight(1f),
                )
            }
        },
    )
}
