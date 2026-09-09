package com.moive.app.presentation.voting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.Label
import com.moive.app.R
import com.moive.app.core.designsystem.component.bottomsheet.MoiveBottomSheet
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.button.MoiveIconButton
import com.moive.app.core.designsystem.component.button.MoiveIconButtonSize
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.addBitmapMarker
import com.moive.app.data.voting.model.PlaceRecommendationCardItemModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListContent(
    locationX: Double,
    locationY: Double,
    regionName: String,
    places: PersistentList<PlaceRecommendationCardItemModel>,
    selectedPlaceIds: PersistentSet<Long>,
    isPlaceListVisible: Boolean,
    onRegionPinClick: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onCheckboxClick: (Long) -> Unit,
    onBottomSheetDismiss: () -> Unit,
    onResetClick: () -> Unit,
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

    LaunchedEffect(kakaoMapState, regionPinBitmap, locationX, locationY) {
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
            MoiveBottomSheet(
                title = "추천 장소",
                modifier = Modifier.heightIn(max = 360.dp),
                onDismissRequest = {
                    isBottomSheetExpanded = false
                    onBottomSheetDismiss()
                },
                content = {
                    LazyColumn (
                        contentPadding = PaddingValues(vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(
                            items = places,
                            key = { it.id }
                        ) { place ->
                            PlaceRecommendationCardItem(
                                place = place,
                                isSelected = place.id in selectedPlaceIds,
                                onItemClick = { onPlaceItemClick(place.id) },
                                onSelectClick = { onCheckboxClick(place.id) },
                            )
                        }
                    }
                },
                buttonContent = {
                    Row(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        MoiveIconButton(
                            icon = ImageVector.vectorResource(R.drawable.ic_rotate_ccw_24),
                            size = MoiveIconButtonSize.LARGE,
                            onClick = onResetClick,
                        )

                        MoiveButton(
                            text = "다음",
                            type = MoiveButtonType.PRIMARY,
                            size = MoiveButtonSize.LARGE,
                            onClick = onCompleteButtonClick,
                            modifier = Modifier.weight(1f),
                        )
                    }
                },
            )
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
                PlaceRecommendationCardItemModel(
                    id = 1L,
                    name = "장소명(상호명) 1",
                    category = "카페",
                    address = "서울시 강남구 워시기워시기 123",
                    matchRate = 60,
                    avgTravelMinutes = 36,
                    maxTravelMinutes = 41,
                    tasteMatchCount = 4,
                    tasteMatchTotal = 7,
                    totalTravelMinutes = 34,
                    totalTravelFare = 1_650,
                ),
                PlaceRecommendationCardItemModel(
                    id = 2L,
                    name = "장소명(상호명) 2",
                    category = "식당",
                    address = "서울시 강남구 워시기워시기 456",
                    matchRate = 50,
                    avgTravelMinutes = 36,
                    maxTravelMinutes = 41,
                    tasteMatchCount = 4,
                    tasteMatchTotal = 7,
                    totalTravelMinutes = 34,
                    totalTravelFare = 1_650,
                ),
            ),
            selectedPlaceIds = persistentSetOf(1L),
            isPlaceListVisible = true,
            onRegionPinClick = {},
            onPlaceItemClick = {},
            onCheckboxClick = {},
            onBottomSheetDismiss = {},
            onResetClick = {},
            onCompleteButtonClick = {},
            modifier = Modifier,
        )
    }
}
