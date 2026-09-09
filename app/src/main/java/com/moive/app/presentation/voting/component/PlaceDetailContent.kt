package com.moive.app.presentation.voting.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.moive.app.R
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.chip.MoiveLabelChip
import com.moive.app.core.designsystem.component.image.UrlImage
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.customShadow
import com.moive.app.data.voting.model.PlaceDetailImageItemModel
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.presentation.voting.util.rememberPlaceRouteMapView

@Composable
fun PlaceDetailContent(
    innerPadding: PaddingValues,
    place: PlaceDetailModel,
    regionName: String,
    onBackClick: () -> Unit,
    onKakaoMapClick: () -> Unit,
    onSelectButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val mapView = rememberPlaceRouteMapView(place)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background.default00)
            .padding(innerPadding),
    ) {
        MoiveSubTitleTopBar(
            title = regionName,
            onBackClick = onBackClick,
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                top = 24.dp,
                bottom = 48.dp,
            )
        ) {
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                ) {
                    MoiveLabelChip(
                        style = LabelType.CATEGORY.getStyle(),
                        text = place.category,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = place.placeName,
                        color = colors.text.default,
                        style = typography.title.lgB,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_pin_fill_16),
                            contentDescription = null,
                            tint = colors.icon.secondary,
                        )

                        Text(
                            text = place.address,
                            color = colors.text.secondary,
                            style = typography.label.xsR,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${place.totalMemberCount}명 중 ${place.matchMemberCount}명의 취향과 일치해요.",
                        color = colors.primary.default,
                        style = typography.label.smM,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = colors.primary.sub01,
                                shape = RoundedCornerShape(radius.md)
                            )
                            .background(
                                color = colors.primary.sub03,
                                shape = RoundedCornerShape(radius.md),
                            )
                            .padding(vertical = 11.dp, horizontal = 16.dp),
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = place.imageList,
                        key = { it.id }
                    ) { img ->
                        UrlImage(
                            url = img.imageUrl,
                            modifier = Modifier
                                .width(152.dp)
                                .aspectRatio(152f / 168f)
                                .clip(
                                    shape = RoundedCornerShape(radius.xl)
                                ),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                HorizontalDivider(
                    thickness = 6.dp,
                    color = colors.background.default02,
                )

                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "${place.userName}님의 이동 경로",
                        color = colors.text.default,
                        style = typography.title.lgB,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(
                                shape = RoundedCornerShape(radius.lg),
                            )
                            .pointerInput(mapView) {
                                awaitEachGesture {
                                    awaitFirstDown(requireUnconsumed = false)
                                    mapView.parent?.requestDisallowInterceptTouchEvent(true)
                                }
                            },
                    ) {
                        AndroidView(
                            factory = { mapView },
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colors.background.default02,
                                shape = RoundedCornerShape(radius.xl),
                            )
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                        ) {
                            Text(
                                text = "총 ${place.totalTravelMinutes}분",
                                color = colors.text.default,
                                style = typography.title.mdSb,
                                modifier = Modifier.weight(1f),
                            )

                            Text(
                                text = "${place.travelFare}원",
                                color = colors.text.tertiary,
                                style = typography.label.smM,
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "모두가 평균적으로 ${place.avgTravelMinutes}분 소요돼요.",
                            color = colors.secondary.default,
                            style = typography.label.xsR,
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            TransitTimeRow(
                                iconRes = R.drawable.ic_walking_person_16,
                                time = place.walkMinutes
                            )
                            TransitTimeRow(
                                iconRes = R.drawable.ic_bus_16,
                                time = place.busMinutes
                            )
                            TransitTimeRow(
                                iconRes = R.drawable.ic_subway_16,
                                time = place.subwayMinutes
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .customShadow(
                    shape = RectangleShape,
                    color = colors.shadowBlack8,
                    blur = 12.dp,
                    offsetY = (-2).dp,
                )
                .background(
                    color = colors.background.default00,
                    shape = RectangleShape,
                )
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MoiveButton(
                text = "카카오맵",
                type = MoiveButtonType.TERTIARY,
                size = MoiveButtonSize.LARGE,
                onClick = onKakaoMapClick,
                modifier = Modifier.weight(1f),
            )

            MoiveButton(
                text = "선택하기",
                type = MoiveButtonType.PRIMARY,
                size = MoiveButtonSize.LARGE,
                onClick = onSelectButtonClick,
                modifier = Modifier.weight(1f),
            )
        }
    }
}


@Composable
private fun TransitTimeRow(
    @DrawableRes iconRes: Int,
    time: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null,
            tint = colors.icon.secondary,
            modifier = Modifier.size(16.dp),
        )

        Text(
            text = "${time}분",
            color = colors.text.secondary,
            style = typography.label.xsR,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceDetailContentPreview() {
    MoiveTheme {
        PlaceDetailContent(
            innerPadding = PaddingValues(),
            place = PlaceDetailModel(
                id = 1L,
                userName = "모이브",
                placeName = "장소명(상호명)",
                category = "카페",
                address = "서울시 강남구 워시기워시기 123",
                totalMemberCount = 7,
                matchMemberCount = 4,
                imageList = listOf(
                    PlaceDetailImageItemModel(id = 1L, imageUrl = ""),
                    PlaceDetailImageItemModel(id = 2L, imageUrl = ""),
                ),
                startPinLatLang = PlaceDetailPinLatLang(
                    latitude = 37.5044,
                    longitude = 127.0246,
                ),
                endPinLatLang = PlaceDetailPinLatLang(
                    latitude = 37.5089,
                    longitude = 127.0632,
                ),
                routeLatLang = PlaceDetailRouteLatLang(
                    latitude = listOf(
                        37.5044, 37.5054217, 37.505525, 37.5049788, 37.50665,
                        37.5083212, 37.507775, 37.5078783, 37.5089,
                    ),
                    longitude = listOf(
                        127.0246, 127.0291954, 127.03425, 127.0396293, 127.0439,
                        127.0481707, 127.05355, 127.0586046, 127.0632,
                    ),
                ),
                totalTravelMinutes = 34,
                avgTravelMinutes = 36,
                walkMinutes = 10,
                busMinutes = 15,
                subwayMinutes = 9,
                travelFare = 1_650,
            ),
            regionName = "신논현동",
            onBackClick = {},
            onKakaoMapClick = {},
            onSelectButtonClick = {},
        )
    }
}
