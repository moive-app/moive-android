package com.moive.app.presentation.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ConfirmBottomSheet(
    title: String,
    description: String,
    btnText: String,
    onDismissRequest: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MoiveBottomSheet(
        title = "",
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = Modifier.padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "$title 하시겠어요?",
                    color = colors.text.default,
                    style = typography.title.mdSb,
                )

                Text(
                    text = description,
                    color = colors.text.subtle,
                    style = typography.body.smNormalR,
                )
            }
        },
        buttonContent = {
            Column(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                MoiveButton(
                    text = btnText,
                    type = MoiveButtonType.PRIMARY,
                    size = MoiveButtonSize.LARGE,
                    onClick = onButtonClick,
                )

                MoiveButton(
                    text = "취소",
                    type = MoiveButtonType.TERTIARY,
                    size = MoiveButtonSize.LARGE,
                    onClick = onDismissRequest,
                )
            }
        }
    )
}
