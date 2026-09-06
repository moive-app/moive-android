package com.moive.app.core.designsystem.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MoiveMultiSelectChipList(
    items: ImmutableList<String>,
    selectedItems: ImmutableList<String>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            MoiveChip(
                type = ChipType.SELECT,
                text = item,
                isSelected = item in selectedItems,
                onTabClick = { onItemClick(item) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveMultiSelectChipListPreview() {
    MoiveTheme {
        val items = persistentListOf("친목·만남", "기념·축하", "네트워킹·교류", "스터디·학습", "취미·여가", "기타")
        var selectedItems by remember { mutableStateOf(persistentListOf(items.first())) }

        MoiveMultiSelectChipList(
            items = items,
            selectedItems = selectedItems,
            onItemClick = { clicked ->
                selectedItems = if (clicked in selectedItems) {
                    selectedItems.remove(clicked)
                } else {
                    selectedItems.add(clicked)
                }
            },
        )
    }
}
