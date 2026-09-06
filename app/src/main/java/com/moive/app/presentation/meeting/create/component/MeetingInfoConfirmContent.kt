package com.moive.app.presentation.meeting.create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.common.component.ShadowButton

@Composable
fun MeetingInfoConfirmContent(
    meetingName: String,
    schedule: String,
    purpose: String,
    onBackClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val isContentScrollable by remember {
        derivedStateOf { lazyListState.canScrollForward || lazyListState.canScrollBackward }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background.default00),
    ) {
        MoiveSubTitleTopBar(
            title = "모임 정보 확인",
            onBackClick = onBackClick,
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 39.dp,
            ),
        ) {
            item {
                Text(
                    text = "입력한 조건을 확인해주세요",
                    color = colors.text.default,
                    style = typography.title.smSb,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colors.stroke.default03,
                            shape = RoundedCornerShape(radius.lg),
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    MeetingInfoRow(
                        label = "모임 이름",
                        value = meetingName
                    )

                    if (schedule.isNotEmpty()) {
                        MeetingInfoRow(
                            label = "일정",
                            value = schedule.ifEmpty { "미정" }
                        )
                    }

                    MeetingInfoRow(
                        label = "모임 목적",
                        value = purpose,
                    )
                }
            }
        }

        ShadowButton(
            text = "확인",
            isEnabled = true,
            onClick = onConfirmButtonClick,
            showShadow = isContentScrollable,
        )
    }
}

@Composable
private fun MeetingInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = label,
            color = colors.text.tertiary,
            style = typography.label.xsM,
        )

        Text(
            text = value,
            color = colors.text.default,
            style = typography.label.smM,
        )
    }
}
