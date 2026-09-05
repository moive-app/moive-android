package com.moive.app.presentation.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.chip.ChipType
import com.moive.app.core.designsystem.component.chip.MoiveChip
import com.moive.app.core.designsystem.theme.MoiveTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TabChipList(
    tabs: ImmutableList<String>,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tabs.forEach { tab ->
            MoiveChip(
                type = ChipType.TAB,
                text = tab,
                isSelected = tab == selectedTab,
                onTabClick = { onTabClick(tab) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabChipListPreview() {
    MoiveTheme {
        val tabs = persistentListOf("전체", "예정", "지난 모임")
        var selectedTab by remember { mutableStateOf(tabs.first()) }

        TabChipList(
            tabs = tabs,
            selectedTab = selectedTab,
            onTabClick = { selectedTab = it },
        )
    }
}
