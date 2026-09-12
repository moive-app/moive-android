package com.moive.app.presentation.meeting.confirmed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.image.UrlImage
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.data.meeting.model.MeetingResultParticipantModel

@Composable
fun TravelParticipantItem(
    participant: MeetingResultParticipantModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.fill.default06,
                shape = RoundedCornerShape(radius.md),
            )
            .padding(start = 6.dp, end = 16.dp)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        UrlImage(
            url = participant.profileImageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(44.dp)
                .clip(
                    shape =RoundedCornerShape(radius.sm)
                ),
        )

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = participant.name,
                color = colors.text.default,
                style = typography.label.smM,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = participant.address,
                color = colors.text.tertiary,
                style = typography.label.xxsR,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = if (participant.transferCount <= 0) "환승 없음" else "환승 ${participant.transferCount}회",
                color = colors.text.default,
                style = typography.label.xxsR,
            )

            Text(
                text = "${participant.travelMinutes}분",
                color = colors.text.tertiary,
                style = typography.label.xxsR,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TravelParticipantItemPreview() {
    MoiveTheme {
        TravelParticipantItem(
            participant = MeetingResultParticipantModel(
                id = 1L,
                name = "다인",
                profileImageUrl = "",
                address = "서울시 구로구 머시기",
                transferCount = 1,
                travelMinutes = 32,
            ),
        )
    }
}
