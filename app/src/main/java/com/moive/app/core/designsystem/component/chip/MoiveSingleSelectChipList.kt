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
fun MoiveSingleSelectChipList(
    items: ImmutableList<String>,
    selectedItem: String?,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            MoiveChip(
                type = ChipType.SELECT,
                text = item,
                isSelected = item == selectedItem,
                onTabClick = { onItemClick(item) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveSingleSelectChipListPreview() {
    MoiveTheme {
        val items = persistentListOf("친목·만남", "기념·축하", "네트워킹·교류", "스터디·학습", "취미·여가", "기타")
        var selectedItem by remember { mutableStateOf(items.first()) }

        MoiveSingleSelectChipList(
            items = items,
            selectedItem = selectedItem,
            onItemClick = { selectedItem = it },
        )
    }
}
