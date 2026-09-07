package com.moive.app.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.moive.app.core.extensions.customShadow
import com.moive.app.core.extensions.noRippleClickable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MyMeetingCardItem(
    title: String,
    dateTime: String,
    participantImageList: ImmutableList<String>,
    statusText: String,
    statusLabelType: LabelType,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit = {},
    extraCount: Int = 0,
) {
    val shape = RoundedCornerShape(radius.xl)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                shape = shape,
                color = colors.shadow8,
                blur = 20.dp,
                offsetY = 4.dp,
            )
            .border(
                width = 1.dp,
                color = colors.stroke.default05,
                shape = shape,
            )
            .background(
                color = colors.fill.default08,
                shape = shape,
            )
            .noRippleClickable(onClick = onCardClick)
            .padding(20.dp),
    ) {
        Text(
            text = title,
            color = colors.text.default,
            style = typography.title.mdSb,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_fill_16),
                contentDescription = null,
                tint = colors.icon.disabled,
            )

            Text(
                text = dateTime,
                color = colors.text.tertiary,
                style = typography.body.smNormalR,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ParticipantAvatars(
                profileList = participantImageList,
                extraCount = extraCount,
                borderColor = colors.stroke.onBg,
            )

            Spacer(modifier = Modifier.weight(1f))

            MoiveLabelChip(
                style = statusLabelType.getStyle(),
                text = statusText,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyMeetingCardItemPreview() {
    MoiveTheme {
        MyMeetingCardItem(
            title = "주말 맛집 모임",
            dateTime = "8월 29일 14:00",
            participantImageList = persistentListOf("", "", ""),
            extraCount = 2,
            statusText = "조건 입력중",
            statusLabelType = LabelType.CONDITION,
            modifier = Modifier.padding(20.dp),
        )
    }
}
