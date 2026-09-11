package com.moive.app.presentation.meeting.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.OnBottomReached
import com.moive.app.data.meeting.model.MeetingListCardItemModel
import com.moive.app.presentation.common.component.MyMeetingCardItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MeetingCardList(
    meetings: ImmutableList<MeetingListCardItemModel>,
    onMeetingClick: (Long) -> Unit,
    isLoading: Boolean,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    listState.OnBottomReached(
        threshold = 3,
        isLoading = isLoading,
        onLoadMore = onLoadMore,
    )

    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(top = 14.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = meetings,
            key = { it.id }
        ) { meeting ->
            MyMeetingCardItem(
                title = meeting.title,
                dateTime = meeting.dateTime ?: "일정 미정",
                participantImageList = meeting.participantImageUrls,
                extraCount = meeting.extraParticipantCount,
                statusText = meeting.statusText,
                statusLabelType = meeting.statusLabelType,
                onCardClick = { onMeetingClick(meeting.id) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingCardListPreview() {
    MoiveTheme {
        MeetingCardList(
            meetings = persistentListOf(
                MeetingListCardItemModel(
                    id = 1L,
                    title = "주말 맛집 모임",
                    dateTime = "8월 29일 14:00",
                    participantImageUrls = persistentListOf("", "", ""),
                    extraParticipantCount = 2,
                    statusText = "조건 입력중",
                    statusLabelType = LabelType.CONDITION,
                ),
                MeetingListCardItemModel(
                    id = 2L,
                    title = "동기 모임",
                    dateTime = "9월 5일 19:00",
                    participantImageUrls = persistentListOf(""),
                    statusText = "투표중",
                    statusLabelType = LabelType.VOTING,
                ),
                MeetingListCardItemModel(
                    id = 3L,
                    title = "스터디 회식",
                    dateTime = "9월 10일 18:30",
                    participantImageUrls = persistentListOf("", ""),
                    statusText = "확정",
                    statusLabelType = LabelType.CONFIRMED,
                ),
            ),
            onMeetingClick = {},
            isLoading = false,
            onLoadMore = {},
            modifier = Modifier.padding(20.dp),
        )
    }
}
