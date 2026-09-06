package com.moive.app.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.data.home.model.MeetingCardItemModel
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MeetingCardList(
    meetings: List<MeetingCardItemModel>,
    onMeetingClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = meetings,
            key = { it.id }
        ) { meeting ->
            MeetingCard(
                title = meeting.title,
                dateTime = meeting.dateTime,
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
            meetings = listOf(
                MeetingCardItemModel(
                    id = 1L,
                    title = "주말 맛집 모임",
                    dateTime = "8월 29일 14:00",
                    participantImageUrls = persistentListOf("", "", ""),
                    extraParticipantCount = 2,
                    statusText = "조건 입력중",
                    statusLabelType = LabelType.CONDITION,
                ),
                MeetingCardItemModel(
                    id = 2L,
                    title = "동기 모임",
                    dateTime = "9월 5일 19:00",
                    participantImageUrls = persistentListOf(""),
                    statusText = "투표중",
                    statusLabelType = LabelType.VOTING,
                ),
                MeetingCardItemModel(
                    id = 3L,
                    title = "스터디 회식",
                    dateTime = "9월 10일 18:30",
                    participantImageUrls = persistentListOf("", ""),
                    statusText = "확정",
                    statusLabelType = LabelType.CONFIRMED,
                ),
            ),
            onMeetingClick = {},
            modifier = Modifier.padding(20.dp),
        )
    }
}
