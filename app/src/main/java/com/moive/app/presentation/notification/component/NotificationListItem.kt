package com.moive.app.presentation.notification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.data.notification.model.NotificationItemModel

@Composable
fun NotificationListItem(
    item: NotificationItemModel,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = colors.fill.default08,
                    shape = RoundedCornerShape(radius.xl)
                )
                .noRippleClickable(onClick = onItemClick)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_mail_20),
                contentDescription = null,
                tint = colors.icon.default,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = item.title,
                        color = colors.text.default,
                        style = typography.title.smSb,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = item.time,
                        color = colors.text.subtle,
                        style = typography.label.xxsR,
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }

                Text(
                    text = item.description,
                    color = colors.text.secondary,
                    style = typography.label.xsR,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (!item.isRead) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .size(6.dp)
                    .background(
                        color = colors.primary.default,
                        shape = RoundedCornerShape(radius.circular),
                    ),
            )
        }
    }
}

@Preview
@Composable
private fun NotificationListItemPreview() {
    MoiveTheme {
        NotificationListItem(
            item = NotificationItemModel(
                id = 1L,
                title = "조건 입력을 완료해주세요",
                description = "'주말 맛집 모임'의 조건을 아직 입력하지 않았어요.",
                time = "10분 전",
                isRead = false,
            ),
            onItemClick = {},
        )
    }
}
