package com.moive.app.presentation.meeting.complete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.chip.MoiveLabelChip
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun CompletedPlaceCard(
    isPlaceConfirmed: Boolean,
    placeName: String,
    placeCategory: String,
    placeAddress: String,
    meetingDate: String,
    meetingTime: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.fill.default08,
                shape = RoundedCornerShape(radius.xl),
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (isPlaceConfirmed) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = placeName,
                        color = colors.text.default,
                        style = typography.title.mdSb,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    MoiveLabelChip(
                        style = LabelType.CATEGORY.getStyle(),
                        text = placeCategory,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_pin_fill_16),
                        contentDescription = null,
                        tint = colors.icon.secondary,
                    )

                    Text(
                        text = placeAddress,
                        color = colors.text.secondary,
                        style = typography.label.xsR,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            } else {
                Text(
                    text = "장소는 따로 정하셨네요!",
                    color = colors.text.default,
                    style = typography.title.mdSb,
                )

                Text(
                    text = "직접 정한 장소는 마음에 드셨나요?",
                    color = colors.secondary.default,
                    style = typography.label.xsR,
                )
            }
        }

        VerticalDivider(
            modifier = Modifier.height(44.dp),
            thickness = 1.dp,
            color = colors.stroke.default03,
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "${meetingDate}\n${meetingTime}",
            color = colors.primary.default,
            style = typography.label.smM,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CompletedPlaceCardConfirmedPreview() {
    MoiveTheme {
        CompletedPlaceCard(
            isPlaceConfirmed = true,
            placeName = "장소명(상호명)",
            placeCategory = "카페",
            placeAddress = "서울시 강남구 OO동",
            meetingDate = "9월 18일",
            meetingTime = "오후 6:00",
        )
    }
}

@Preview(showBackground = true, name = "장소 미정")
@Composable
private fun CompletedPlaceCardUndecidedPreview() {
    MoiveTheme {
        CompletedPlaceCard(
            isPlaceConfirmed = false,
            placeName = "",
            placeCategory = "",
            placeAddress = "",
            meetingDate = "9월 18일",
            meetingTime = "오후 6:00",
        )
    }
}
