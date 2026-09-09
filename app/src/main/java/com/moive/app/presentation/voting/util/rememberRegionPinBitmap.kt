package com.moive.app.presentation.voting.util

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.annotation.DrawableRes
import com.moive.app.R
import com.moive.app.presentation.voting.component.RoutePinBitmap

private val regionPinImgRes = listOf(
    R.drawable.img_pin_marker_1,
    R.drawable.img_pin_marker_2,
    R.drawable.img_pin_marker_3,
)

private const val REGION_PIN_ANCHOR_X = 0.5f
private const val REGION_PIN_ANCHOR_Y = 0.99f

@Composable
fun rememberRegionPinBitmap(rank: Int): RoutePinBitmap? {
    @DrawableRes val drawableRes = regionPinImgRes.getOrElse(rank) { regionPinImgRes.last() }

    val graphicsLayer = rememberGraphicsLayer()
    var pinResult by remember { mutableStateOf<RoutePinBitmap?>(null) }

    Image(
        painter = painterResource(drawableRes),
        contentDescription = null,
        modifier = Modifier
            .offset(x = 10_000.dp)
            .size(width = 26.dp, height = 36.dp)
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            },
    )

    LaunchedEffect(drawableRes) {
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
            anchorX = REGION_PIN_ANCHOR_X,
            anchorY = REGION_PIN_ANCHOR_Y,
        )
    }

    return pinResult
}
