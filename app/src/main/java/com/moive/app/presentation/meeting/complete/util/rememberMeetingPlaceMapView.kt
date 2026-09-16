package com.moive.app.presentation.meeting.complete.util

import android.graphics.Bitmap
import android.os.Build
import android.view.View
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.Label
import com.moive.app.R
import com.moive.app.core.extensions.addBitmapMarker
import com.moive.app.presentation.common.component.placedetail.util.RoutePinBitmap
import com.moive.app.presentation.voting.util.rememberMapViewWithLifecycle

private const val END_PIN_ANCHOR_Y_FRACTION = 30f / 31f

@Composable
fun rememberMeetingPlaceMapView(latitude: Double, longitude: Double, cornerRadius: Dp = 0.dp): View {
    var kakaoMap by remember { mutableStateOf<KakaoMap?>(null) }
    var pinLabel by remember { mutableStateOf<Label?>(null) }
    val pinBitmap = rememberEndPinBitmap()

    val mapView = rememberMapViewWithLifecycle(
        locationX = longitude,
        locationY = latitude,
        onMapReady = { kakaoMap = it },
        cornerRadius = cornerRadius,
    )

    LaunchedEffect(kakaoMap, pinBitmap, latitude, longitude) {
        val map = kakaoMap ?: return@LaunchedEffect
        val bitmap = pinBitmap ?: return@LaunchedEffect

        pinLabel?.remove()
        pinLabel = map.addBitmapMarker(
            position = LatLng.from(latitude, longitude),
            bitmap = bitmap.bitmap,
            anchorX = bitmap.anchorX,
            anchorY = bitmap.anchorY,
        )
    }

    return mapView
}

@Composable
private fun rememberEndPinBitmap(): RoutePinBitmap? {
    val graphicsLayer = rememberGraphicsLayer()
    var pinResult by remember { mutableStateOf<RoutePinBitmap?>(null) }
    var isLaidOut by remember { mutableStateOf(false) }

    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_place_detail_end_pin),
        contentDescription = null,
        tint = Color.Unspecified,
        modifier = Modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(0, 0) {
                    placeable.place(0, 0)
                }
            }
            .offset(x = 10_000.dp)
            .size(width = 22.dp, height = 30.dp)
            .onGloballyPositioned { isLaidOut = true }
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            },
    )

    LaunchedEffect(isLaidOut) {
        if (!isLaidOut) return@LaunchedEffect

        withFrameNanos {}
        withFrameNanos {}

        val captured = graphicsLayer.toImageBitmap().asAndroidBitmap()
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && captured.config == Bitmap.Config.HARDWARE) {
            captured.copy(Bitmap.Config.ARGB_8888, false)
        } else {
            captured
        }

        pinResult = RoutePinBitmap(
            bitmap = bitmap,
            anchorX = 0.5f,
            anchorY = END_PIN_ANCHOR_Y_FRACTION,
        )
    }

    return pinResult
}
