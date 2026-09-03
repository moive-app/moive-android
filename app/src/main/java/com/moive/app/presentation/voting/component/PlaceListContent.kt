package com.moive.app.presentation.voting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moive.app.presentation.voting.PlaceDetailModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet

@Composable
fun PlaceListContent(
    places: PersistentList<PlaceDetailModel>,
    selectedPlaceIds: PersistentSet<Long>,
    isPlaceListVisible: Boolean,
    onRegionPinClick: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onCheckboxClick: (Long) -> Unit,
    onCompleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        // TODO: 카카오맵 연동
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable(onClick = onRegionPinClick),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "지도 (지역 핀 클릭)")
        }

        if (isPlaceListVisible) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
            ) {
                Text(text = "추천 장소 리스트")

                places.forEach { place ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPlaceItemClick(place.id) }
                            .padding(vertical = 8.dp),
                    ) {
                        Checkbox(
                            checked = place.id in selectedPlaceIds,
                            onCheckedChange = { onCheckboxClick(place.id) },
                        )
                        Text(text = place.name)
                    }
                }

                Button(
                    onClick = onCompleteButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    Text(text = "완료")
                }
            }
        }
    }
}
