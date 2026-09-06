package com.moive.app.core.designsystem.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveSubIconTopBar(
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int? = R.drawable.ic_arrow_chevron_left_24,
    onLeadingIconClick: () -> Unit = {},
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null,
                tint = colors.icon.default,
                modifier = Modifier
                    .noRippleClickable(onClick = onLeadingIconClick),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (trailingIcon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .noRippleClickable(onClick = onTrailingIconClick),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveSubIconTopBarPreview() {
    MoiveTheme {
        MoiveSubIconTopBar(
            trailingIcon = R.drawable.ic_more_24,
            onLeadingIconClick = {},
            onTrailingIconClick = {},
        )
    }
}
