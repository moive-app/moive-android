package com.moive.app.presentation.voting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.Label
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.addBitmapMarker
import com.moive.app.presentation.voting.PlaceDetailModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@Composable
fun PlaceListContent(
    locationX: Double,
    locationY: Double,
    regionName: String,
    places: PersistentList<PlaceDetailModel>,
    selectedPlaceIds: PersistentSet<Long>,
    isPlaceListVisible: Boolean,
    onRegionPinClick: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onCheckboxClick: (Long) -> Unit,
    onCompleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var kakaoMapState by remember { mutableStateOf<KakaoMap?>(null) }
    var regionPinLabel by remember { mutableStateOf<Label?>(null) }
    var isBottomSheetExpanded by remember { mutableStateOf(false) }

    val regionPinBitmap = rememberRegionMarkerBitmap(regionName = regionName, isExpanded = isBottomSheetExpanded)

    val mapView = rememberMapViewWithLifecycle(
        locationX = locationX,
        locationY = locationY,
        onMapReady = { kakaoMap ->
            kakaoMap.setOnLabelClickListener { _, _, _ ->
                isBottomSheetExpanded = !isBottomSheetExpanded
                onRegionPinClick()
                true
            }
            kakaoMapState = kakaoMap
        },
    )

    LaunchedEffect(kakaoMapState, regionPinBitmap) {
        val kakaoMap = kakaoMapState ?: return@LaunchedEffect
        val bitmap = regionPinBitmap ?: return@LaunchedEffect

        regionPinLabel?.remove()
        regionPinLabel = kakaoMap.addBitmapMarker(
            position = LatLng.from(locationY, locationX),
            bitmap = bitmap,
        )
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AndroidView(
            factory = { mapView },
        )

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


@Preview
@Composable
private fun PlaceListContentPreview() {
    MoiveTheme {
        PlaceListContent(
            locationX = 127.0276,
            locationY = 37.4979,
            regionName = "신논현동",
            places = persistentListOf(
                PlaceDetailModel(id = 1L, name = "장소명(상호명) 1"),
                PlaceDetailModel(id = 2L, name = "장소명(상호명) 2"),
                PlaceDetailModel(id = 3L, name = "장소명(상호명) 3"),
            ),
            selectedPlaceIds = persistentSetOf(1L),
            isPlaceListVisible = true,
            onRegionPinClick = {},
            onPlaceItemClick = {},
            onCheckboxClick = {},
            onCompleteButtonClick = {},
            modifier = Modifier,
        )
    }
}
