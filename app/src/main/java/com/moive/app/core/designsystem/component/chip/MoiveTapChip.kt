package com.moive.app.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveTapChip(
    text: String,
    isSelected: Boolean,
    onTabClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (isSelected) colors.gray800 else colors.gray200,
                shape = RoundedCornerShape(radius.circular),
            )
            .background(
                color = if (isSelected) colors.gray800 else colors.gray01,
                shape = RoundedCornerShape(radius.circular)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .noRippleClickable(onClick = onTabClick),
    ) {
        Text(
            text = text,
            color = if (isSelected) colors.gray01 else colors.gray800,
            style = typography.label.smM,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveTapChipPreview() {
    MoiveTheme {
        val tabs = listOf("tab1", "tab2")
        var selectedTab by remember { mutableStateOf(tabs.first()) }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tabs.forEach { tab ->
                MoiveTapChip(
                    text = tab,
                    isSelected = tab == selectedTab,
                    onTabClick = { selectedTab = tab },
                )
            }
        }
    }
}
