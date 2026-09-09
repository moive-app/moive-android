package com.moive.app.presentation.voting.util

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.route.RouteLine
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.extensions.addBitmapMarker
import com.moive.app.core.extensions.addRouteLine
import com.moive.app.data.voting.model.PlaceDetailModel
import com.moive.app.data.voting.model.PlaceDetailPinLatLang
import com.moive.app.data.voting.model.PlaceDetailRouteLatLang
import com.moive.app.presentation.voting.component.RoutePinBitmap
import com.moive.app.presentation.voting.component.rememberRoutePinBitmap

@Composable
fun rememberPlaceRouteMapView(place: PlaceDetailModel): View {
    var kakaoMap by remember { mutableStateOf<KakaoMap?>(null) }
    var startPinLabel by remember { mutableStateOf<Label?>(null) }
    var endPinLabel by remember { mutableStateOf<Label?>(null) }
    var routeLine by remember { mutableStateOf<RouteLine?>(null) }

    val startPinBitmap = rememberRoutePinBitmap(label = "출발", isStartLabel = true)
    val endPinBitmap = rememberRoutePinBitmap(label = "도착", isStartLabel = false)
    val routeLineColor = colors.primary.default.toArgb()

    val mapView = rememberMapViewWithLifecycle(
        locationX = (place.startPinLatLang.longitude + place.endPinLatLang.longitude) / 2,
        locationY = (place.startPinLatLang.latitude + place.endPinLatLang.latitude) / 2,
        onMapReady = { kakaoMap = it },
    )

    LaunchedEffect(kakaoMap, startPinBitmap, endPinBitmap, place.routeLatLang) {
        val map = kakaoMap ?: return@LaunchedEffect
        val startBitmap = startPinBitmap ?: return@LaunchedEffect
        val endBitmap = endPinBitmap ?: return@LaunchedEffect

        startPinLabel?.remove()
        endPinLabel?.remove()
        routeLine?.remove()

        routeLine = map.addRouteLine(
            points = place.routeLatLang.toLatLngList(),
            lineWidth = 6f,
            lineColor = routeLineColor,
        )
        startPinLabel = map.addPinMarker(place.startPinLatLang, startBitmap)
        endPinLabel = map.addPinMarker(place.endPinLatLang, endBitmap)
    }

    return mapView
}

private fun PlaceDetailRouteLatLang.toLatLngList(): List<LatLng> =
    latitude.zip(longitude) { lat, lng -> LatLng.from(lat, lng) }

private fun KakaoMap.addPinMarker(position: PlaceDetailPinLatLang, bitmap: RoutePinBitmap): Label? =
    addBitmapMarker(
        position = LatLng.from(position.latitude, position.longitude),
        bitmap = bitmap.bitmap,
        anchorX = bitmap.anchorX,
        anchorY = bitmap.anchorY,
    )
