package com.moive.app.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveButton(
    text: String,
    type: MoiveButtonType,
    size: MoiveButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val style = type.getStyle(enabled = enabled, isPressed = isPressed)
    val shape = RoundedCornerShape(radius.md)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = style.borderColor ?: Color.Transparent,
                shape = shape
            )
            .background(
                color = style.containerColor,
                shape = shape
            )
            .noRippleClickable(
                onClick = onClick,
                isEnabled = enabled,
                interactionSource = interactionSource,
            )
            .padding(vertical = size.verticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = style.contentColor,
            style = size.textStyle(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveButtonPreview() {
    MoiveTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            MoiveButtonType.entries.forEach { type ->
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    MoiveButtonSize.entries.forEach { size ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            MoiveButton(
                                text = "Label",
                                onClick = {},
                                type = type,
                                size = size,
                                modifier = Modifier.weight(1f),
                            )
                            MoiveButton(
                                text = "Label",
                                onClick = {},
                                type = type,
                                size = size,
                                enabled = false,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        }
    }
}
