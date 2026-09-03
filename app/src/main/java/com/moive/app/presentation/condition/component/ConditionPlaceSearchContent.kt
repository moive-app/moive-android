package com.moive.app.presentation.condition.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val placeholderPlaceNames = listOf("강남역", "홍대입구역", "건대입구역")

@Composable
fun ConditionPlaceSearchContent(
    onPlaceItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "위치 검색")

        placeholderPlaceNames.forEach { placeName ->
            Text(
                text = placeName,
                modifier = Modifier
                    .clickable { onPlaceItemClick(placeName) }
                    .padding(vertical = 12.dp),
            )
        }
    }
}
