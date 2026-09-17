package com.moive.app.presentation.meeting.detail.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun MeetingInfoRow(
    @DrawableRes thumbnailRes: Int,
    meetingName: String,
    meetingPurpose: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(thumbnailRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(radius.md)),
        )

        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ){
            Text(
                text = meetingName,
                color = colors.text.default,
                style = typography.title.mdSb,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = meetingPurpose,
                color = colors.text.tertiary,
                style = typography.body.mdNormalR,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
