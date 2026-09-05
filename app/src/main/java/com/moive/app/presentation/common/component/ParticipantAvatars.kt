package com.moive.app.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.image.UrlImage
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ParticipantAvatars(
    profileList: ImmutableList<String>,
    extraCount: Int,
    modifier: Modifier = Modifier,
) {
    val circle = RoundedCornerShape(radius.circular)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-8).dp),
    ) {
        profileList.forEach { url ->
            UrlImage(
                url = url,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(24.dp)
                    .clip(circle)
                    .border(
                        width = 1.dp,
                        color = colors.stroke.onBg,
                        shape = circle,
                    ),
            )
        }
        if (extraCount > 0) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = colors.fill.default08,
                        shape = circle
                    )
                    .clip(circle)
                    .border(
                        width = 1.dp,
                        color = colors.stroke.onBg,
                        shape = circle,
                    ),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "+$extraCount",
                    color = colors.text.tertiary,
                    style = typography.label.xsR,
                )
            }
        }
    }
}
