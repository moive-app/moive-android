package com.moive.app.core.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.moive.app.R
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoiveBottomSheet(
    title: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = { },
    bottomSheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { targetValue ->
            targetValue != SheetValue.Hidden
        }
    ),
    showScrim: Boolean = true,
    content: @Composable () -> Unit,
    buttonContent: @Composable () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = bottomSheetState,
        shape = RoundedCornerShape(
            topStart = radius.xxl,
            topEnd = radius.xxl,
        ),
        containerColor = colors.background.default00,
        scrimColor = if (showScrim) colors.dim.default else Color.Transparent,
        dragHandle = null,
        content = {
            BottomSheetContent(
                title = title,
                onCloseClick = onDismissRequest,
                content = content,
                buttonContent = buttonContent,
            )
        }
    )
}

@Composable
private fun BottomSheetContent(
    title: String,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    buttonContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                color = colors.text.default,
                style = typography.title.lgB,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_close_md_20),
                contentDescription = null,
                modifier = Modifier
                    .noRippleClickable(
                        onClick = onCloseClick
                    )

            )
        }

        content()

        Spacer(modifier = Modifier.height(10.dp))

        buttonContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MoiveBottomSheetPreview() {
    MoiveTheme {
        var showBottomSheet by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MoiveButton(
                text = "바텀시트 열기",
                type = MoiveButtonType.PURPLE,
                size = MoiveButtonSize.LARGE,
                onClick = { showBottomSheet = true },
            )
        }

        if (showBottomSheet) {
            MoiveBottomSheet(
                title = "타이틀",
                onDismissRequest = { showBottomSheet = false },
                content = {
                    Spacer(modifier = Modifier.height(300.dp))
                },
                buttonContent = {
                    MoiveButton(
                        text = "다음",
                        type = MoiveButtonType.PURPLE,
                        size = MoiveButtonSize.LARGE,
                        onClick = {},
                    )
                }
            )
        }
    }
}
