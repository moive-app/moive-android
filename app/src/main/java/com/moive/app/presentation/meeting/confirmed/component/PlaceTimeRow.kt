package com.moive.app.presentation.meeting.confirmed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun PlaceTimeRow(
    isPlaceConfirmed: Boolean,
    placeName: String,
    placeCategory: String,
    placeAddress: String,
    meetingDate: String,
    meetingTime: String,
    onPlaceClick: () -> Unit,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = if (isPlaceConfirmed) placeName else "장소를 따로 정해주세요.",
                    color = colors.text.default,
                    style = typography.title.mdSb,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                if(isPlaceConfirmed){
                    MoiveLabelChip(
                        style = LabelType.CATEGORY.getStyle(),
                        text = placeCategory,
                    )
                }
            }

            if (isPlaceConfirmed){
                Row(
                    modifier = Modifier.noRippleClickable(onClick = onPlaceClick),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_pin_fill_16),
                        contentDescription = null,
                        tint = colors.icon.tertiary,
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = placeAddress,
                        color = colors.text.secondary,
                        style = typography.label.xsR,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_chevron_right_16),
                        contentDescription = null,
                        tint = colors.icon.secondary,
                    )
                }
            } else {
                Text(
                    text = "추천 장소를 투표하지 않았어요.",
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
private fun PlaceTimeRowPreview() {
    MoiveTheme {
        PlaceTimeRow(
            isPlaceConfirmed = true,
            placeName = "장소명(상호명)",
            placeCategory = "카페",
            placeAddress = "서울시 강남구 OO동",
            meetingDate = "9월 18일",
            meetingTime = "오후 6:00",
            onPlaceClick = {},
        )
    }
}
