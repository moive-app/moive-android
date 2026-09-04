package com.moive.app.presentation.voting.component

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.chip.MoiveLabelChip

@Composable
fun rememberRegionMarkerBitmap(
    regionName: String,
    isExpanded: Boolean,
): Bitmap? {
    val graphicsLayer = rememberGraphicsLayer()
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .offset(x = 10_000.dp)
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            },
    ) {
        if (isExpanded) {
            MoiveLabelChip(
                style = LabelType.VOTING.getStyle(),
                text = regionName,
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_profile_28),
            contentDescription = null,
            modifier = Modifier.size(28.dp),
        )

    }

    LaunchedEffect(regionName, isExpanded) {
        withFrameNanos {}
        withFrameNanos {}

        val captured = graphicsLayer.toImageBitmap().asAndroidBitmap()

        bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && captured.config == Bitmap.Config.HARDWARE) {
            captured.copy(Bitmap.Config.ARGB_8888, false)
        } else {
            captured
        }
    }
    return bitmap
}
