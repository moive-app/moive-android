package com.moive.app.core.designsystem.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveSubTitleTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.fill.default08
            )
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_chevron_left_24),
            contentDescription = null,
            tint = colors.icon.default,
            modifier = Modifier
                .noRippleClickable(onClick = onBackClick),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            color = colors.text.default,
            style = typography.title.mdSb,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveSubTitleTopBarPreview() {
    MoiveTheme {
        MoiveSubTitleTopBar(
            title = "title",
            onBackClick = {},
        )
    }
}
