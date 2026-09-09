package com.moive.app.presentation.voting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
import com.moive.app.data.voting.model.PlaceRecommendationCardItemModel

@Composable
fun PlaceRecommendationCardItem(
    place: PlaceRecommendationCardItemModel,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    onSelectClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) colors.primary.default else colors.stroke.default03,
                shape = RoundedCornerShape(radius.lg),
            )
            .background(
                color = colors.fill.default08,
                shape = RoundedCornerShape(radius.lg),
            )
            .noRippleClickable(onClick = onItemClick)
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = place.name,
                color = colors.text.default,
                style = typography.title.mdSb,
                modifier = Modifier.padding(end = 28.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                MoiveLabelChip(
                    style = LabelType.CATEGORY.getStyle(),
                    text = place.category,
                )

                MoiveLabelChip(
                    style = LabelType.INFO.getStyle(),
                    text = "선호도 일치율 ${place.matchRate}%",
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlaceStat(
                    label = "평균 이동",
                    value = "${place.avgTravelMinutes}분",
                    modifier = Modifier.weight(1f),
                )

                VerticalDivider(
                    modifier = Modifier.height(20.dp),
                    thickness = 1.dp,
                    color = colors.stroke.default03,
                )

                PlaceStat(
                    label = "최장 이동",
                    value = "${place.maxTravelMinutes}분",
                    modifier = Modifier.weight(1f),
                )

                VerticalDivider(
                    modifier = Modifier.height(20.dp),
                    thickness = 1.dp,
                    color = colors.stroke.default03,
                )

                PlaceStat(
                    label = "취향 일치",
                    value = "${place.tasteMatchCount}명",
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Icon(
            imageVector = ImageVector.vectorResource(
                if (isSelected) R.drawable.ic_check_pressed_20 else R.drawable.ic_check_disabled_20,
            ),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .noRippleClickable(onClick = onSelectClick),
        )
    }
}

@Composable
private fun PlaceStat(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = label,
            color = colors.text.default,
            style = typography.label.xsM,
        )

        Text(
            text = value,
            color = colors.text.subtle,
            style = typography.label.xsR,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceRecommendationCardItemPreview() {
    MoiveTheme {
        PlaceRecommendationCardItem(
            place = PlaceRecommendationCardItemModel(
                id = 1L,
                name = "장소명(상호명) 1dddddddddddddddddddddddddddddddddddddddddd",
                category = "카페",
                address = "서울시 강남구 워시기워시기 123",
                matchRate = 60,
                avgTravelMinutes = 36,
                maxTravelMinutes = 41,
                tasteMatchCount = 4,
                tasteMatchTotal = 7,
                totalTravelMinutes = 34,
                totalTravelFare = 1_650,
            ),
            isSelected = true,
            onItemClick = {},
            onSelectClick = {},
        )
    }
}
