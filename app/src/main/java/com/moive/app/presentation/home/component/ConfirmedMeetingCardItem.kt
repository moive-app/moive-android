package com.moive.app.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.chip.MoiveLabelChip
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.common.component.ParticipantAvatars
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ConfirmedMeetingCardItem(
    title: String,
    dateTime: String,
    location: String,
    participantImageList: ImmutableList<String>,
    dDayText: String,
    cardColor: Color,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    extraCount: Int = 0,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(252f/220f)
            .background(
                color = cardColor,
                shape = RoundedCornerShape(radius.xxl),
            )
            .noRippleClickable(onClick = onCardClick)
            .padding(20.dp),
    ) {
        Text(
            text = title,
            color = colors.text.onBg,
            style = typography.title.mdSb,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_fill_16),
                contentDescription = null,
                tint = colors.icon.onBgSub,
            )

            Text(
                text = dateTime,
                color = colors.text.onBg,
                style = typography.body.smNormalR,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_pin_fill_16),
                contentDescription = null,
                tint = colors.icon.onBgSub,
            )

            Text(
                text = location,
                color = colors.text.onBg,
                style = typography.body.smNormalR,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ParticipantAvatars(
                profileList = participantImageList,
                extraCount = extraCount,
                borderColor = cardColor,
            )

            Spacer(modifier = Modifier.weight(1f))

            MoiveLabelChip(
                style = LabelType.DATE.getStyle(),
                text = dDayText,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmedMeetingCardItemPreview() {
    MoiveTheme {
        ConfirmedMeetingCardItem(
            title = "강남에서 만나자",
            dateTime = "8월 29일 14:00",
            location = "홍대입구역 2번 출구",
            participantImageList = persistentListOf("", "", ""),
            extraCount = 2,
            dDayText = "D-5",
            cardColor = colors.primary.default,
            onCardClick = {},
            modifier = Modifier.padding(20.dp),
        )
    }
}
