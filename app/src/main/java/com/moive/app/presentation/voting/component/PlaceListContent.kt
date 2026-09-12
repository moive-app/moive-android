package com.moive.app.presentation.voting.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.Label
import com.moive.app.R
import com.moive.app.core.designsystem.component.bottomsheet.MoiveBottomSheet
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.button.MoiveIconButton
import com.moive.app.core.designsystem.component.button.MoiveIconButtonSize
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.extensions.addBitmapMarker
import com.moive.app.data.voting.model.PlaceRecommendedPlaceCardItemModel
import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.presentation.voting.util.rememberMapViewWithLifecycle
import com.moive.app.presentation.voting.util.rememberRegionPinBitmap
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListContent(
    innerPadding: PaddingValues,
    regionList: ImmutableList<RegionPinModel>,
    selectedRegionName: String?,
    places: ImmutableList<PlaceRecommendedPlaceCardItemModel>,
    selectedPlaceIds: PersistentSet<Long>,
    isPlaceListVisible: Boolean,
    onRegionPinClick: (RegionPinModel) -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onCheckboxClick: (Long) -> Unit,
    onBottomSheetDismiss: () -> Unit,
    onResetClick: () -> Unit,
    onBackClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCompleteButtonLoading: Boolean = false,
) {
    var kakaoMapState by remember { mutableStateOf<KakaoMap?>(null) }
    var regionPinEntries by remember { mutableStateOf<List<Pair<Label, RegionPinModel>>>(emptyList()) }

    val regionPinBitmaps = List(regionList.size) { index -> rememberRegionPinBitmap(rank = index) }

    val mapCenterX = if (regionList.isEmpty()) DEFAULT_LOCATION_X else regionList.map { it.locationX }.average()
    val mapCenterY = if (regionList.isEmpty()) DEFAULT_LOCATION_Y else regionList.map { it.locationY }.average()

    val mapView = rememberMapViewWithLifecycle(
        locationX = mapCenterX,
        locationY = mapCenterY,
        onMapReady = { kakaoMap ->
            kakaoMap.setOnLabelClickListener { map, _, label ->
                val clickedRegion = regionPinEntries.firstOrNull { it.first == label }?.second

                map.moveCamera(
                    CameraUpdateFactory.newCenterPosition(label.position, 16),
                    CameraAnimation.from(300),
                )
                clickedRegion?.let { onRegionPinClick(it) }
                true
            }
            kakaoMapState = kakaoMap
        },
    )

    LaunchedEffect(kakaoMapState, regionList, regionPinBitmaps) {
        val kakaoMap = kakaoMapState ?: return@LaunchedEffect
        if (regionPinBitmaps.any { it == null }) return@LaunchedEffect

        regionPinEntries.forEach { (label, _) -> label.remove() }
        regionPinEntries = regionList.mapIndexedNotNull { index, region ->
            val pinBitmap = regionPinBitmaps.getOrNull(index) ?: return@mapIndexedNotNull null
            val label = kakaoMap.addBitmapMarker(
                position = LatLng.from(region.locationY, region.locationX),
                bitmap = pinBitmap.bitmap,
                anchorX = pinBitmap.anchorX,
                anchorY = pinBitmap.anchorY,
            ) ?: return@mapIndexedNotNull null
            label to region
        }

        if (regionList.isNotEmpty()) {
            val points = regionList.map { LatLng.from(it.locationY, it.locationX) }.toTypedArray()
            kakaoMap.moveCamera(CameraUpdateFactory.fitMapPoints(points, MAP_BOUNDS_PADDING_PX))
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.background.default00,
            )
            .padding(innerPadding),
    ) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
        )

        MoiveSubTitleTopBar(
            title = selectedRegionName ?: "추천 지역",
            onBackClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
        )

        if (isPlaceListVisible) {
            MoiveBottomSheet(
                title = "추천 장소",
                onDismissRequest = onBottomSheetDismiss,
                showScrim = false,
                content = {
                    LazyColumn (
                        contentPadding = PaddingValues(vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.heightIn(max = 360.dp),
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
                            enabled = selectedPlaceIds.isNotEmpty(),
                            isLoading = isCompleteButtonLoading,
                            modifier = Modifier.weight(1f),
                        )
                    }
                },
            )
        }
    }
}

private const val DEFAULT_LOCATION_X = 127.0246 // 신논현역
private const val DEFAULT_LOCATION_Y = 37.5044 // 신논현역
private const val MAP_BOUNDS_PADDING_PX = 300

@Preview
@Composable
private fun PlaceListContentPreview() {
    MoiveTheme {
        PlaceListContent(
            innerPadding = PaddingValues(),
            regionList = persistentListOf(
                RegionPinModel(id = 1L, name = "신논현동", locationX = 127.0246, locationY = 37.5044),
                RegionPinModel(id = 2L, name = "논현동", locationX = 127.0219, locationY = 37.5107),
                RegionPinModel(id = 3L, name = "역삼동", locationX = 127.0364, locationY = 37.5000),
            ),
            selectedRegionName = null,
            places = persistentListOf(
                PlaceRecommendedPlaceCardItemModel(
                    id = 1L,
                    name = "장소명(상호명) 1",
                    category = "카페",
                    matchRate = 60,
                    avgTravelMinutes = 36,
                    maxTravelMinutes = 41,
                    tasteMatchCount = 4,
                ),
                PlaceRecommendedPlaceCardItemModel(
                    id = 2L,
                    name = "장소명(상호명) 2",
                    category = "식당",
                    matchRate = 50,
                    avgTravelMinutes = 36,
                    maxTravelMinutes = 41,
                    tasteMatchCount = 4,
                ),
            ),
            selectedPlaceIds = persistentSetOf(1L),
            isPlaceListVisible = true,
            onRegionPinClick = {},
            onPlaceItemClick = {},
            onCheckboxClick = {},
            onBottomSheetDismiss = {},
            onResetClick = {},
            onBackClick = {},
            onCompleteButtonClick = {},
            modifier = Modifier,
        )
    }
}
