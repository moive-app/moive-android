package com.moive.app.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveIconButton(
    icon: ImageVector,
    size: MoiveIconButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val shape = RoundedCornerShape(radius.md)

    Box(
        modifier = modifier
            .border(
                color = colors.stroke.default03,
                width = 1.dp,
                shape = shape,
            )
            .background(
                color = if (isPressed) colors.fill.default06 else colors.fill.default07,
                shape = shape,
            )
            .noRippleClickable(
                onClick = onClick,
                isEnabled = enabled,
                interactionSource = interactionSource,
            )
            .padding(size.padding),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(size.iconSize),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveIconButtonPreview() {
    MoiveTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MoiveIconButtonSize.entries.forEach { size ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MoiveIconButton(
                        icon = ImageVector.vectorResource(R.drawable.ic_home),
                        onClick = {},
                        size = size,
                    )
                    MoiveIconButton(
                        icon = ImageVector.vectorResource(R.drawable.ic_home),
                        onClick = {},
                        size = size,
                        enabled = false,
                    )
                }
            }
        }
    }
}
