package com.moive.app.presentation.common.component.placedetail.util

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.chip.MoiveLabelChip

@Immutable
data class RoutePinBitmap(
    val bitmap: Bitmap,
    val anchorX: Float,
    val anchorY: Float,
)

@Composable
fun rememberRoutePinBitmap(
    label: String,
    isStartLabel: Boolean,
): RoutePinBitmap? {
    val graphicsLayer = rememberGraphicsLayer()
    var pinResult by remember { mutableStateOf<RoutePinBitmap?>(null) }
    var pinBounds by remember { mutableStateOf<Rect?>(null) }
    var pinSize by remember { mutableStateOf<IntSize?>(null) }

    val pinIconRes = if (isStartLabel) R.drawable.ic_place_detail_start_pin else R.drawable.ic_place_detail_end_pin
    val pinWidth = if (isStartLabel) 12.dp else 22.dp
    val pinHeight = if (isStartLabel) 12.dp else 31.dp
    val pinAnchorYFraction = if (isStartLabel) 0.5f else 30f / 31f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .offset(x = 10_000.dp)
            .onGloballyPositioned { pinSize = it.size }
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            },
    ) {
        if (isStartLabel) {
            MoiveLabelChip(style = LabelType.REGION.getStyle(), text = label)
        }

        Icon(
            imageVector = ImageVector.vectorResource(pinIconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(width = pinWidth, height = pinHeight)
                .onGloballyPositioned { coordinates ->
                    pinBounds = coordinates.boundsInParent()
                },
        )

        if (!isStartLabel) {
            MoiveLabelChip(style = LabelType.REGION.getStyle(), text = label)
        }
    }

    LaunchedEffect(label, isStartLabel) {
        withFrameNanos {}
        withFrameNanos {}

        val size = pinSize ?: return@LaunchedEffect
        val bounds = pinBounds ?: return@LaunchedEffect

        val captured = graphicsLayer.toImageBitmap().asAndroidBitmap()
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && captured.config == Bitmap.Config.HARDWARE) {
            captured.copy(Bitmap.Config.ARGB_8888, false)
        } else {
            captured
        }

        val anchor = Offset(
            x = bounds.left + bounds.width * 0.5f,
            y = bounds.top + bounds.height * pinAnchorYFraction,
        )

        pinResult = RoutePinBitmap(
            bitmap = bitmap,
            anchorX = anchor.x / size.width,
            anchorY = anchor.y / size.height,
        )
    }

    return pinResult
}
