package com.moive.app.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.data.home.model.UpcomingMeetingItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlin.random.Random

@Composable
fun UpcomingMeetingPager(
    meetings: ImmutableList<UpcomingMeetingItemModel>,
    onMeetingClick: (Long) -> Unit,
    onAddMeetingClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pageCount = if (meetings.size == 1) 2 else meetings.size
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val purple = colors.primary.default
    val blue = colors.secondary.default

    Column(
        modifier = modifier,
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 12.dp,
        ) { page ->
            if (page < meetings.size) {
                val meeting = meetings[page]
                val cardColor = remember(meeting.id) {
                    if (Random.nextBoolean()) purple else blue
                }

                UpcomingMeetingCardItem(
                    title = meeting.title,
                    dateTime = meeting.dateTime,
                    location = meeting.location,
                    participantImageList = meeting.participantImageList,
                    extraCount = meeting.extraCount,
                    dDayText = meeting.dDayText,
                    cardColor = cardColor,
                    onCardClick = { onMeetingClick(meeting.id) },
                )
            } else {
                AddMeetingCard(onAddClick = onAddMeetingClick)
            }
        }

        if (pageCount > 1) {
            Spacer(modifier = Modifier.height(24.dp))

            PagerIndicator(
                pageCount = pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage

            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(if (isSelected) 20.dp else 8.dp)
                    .background(
                        color = if (isSelected) colors.fill.default02 else colors.fill.default03,
                        shape = RoundedCornerShape(radius.circular),
                    ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UpcomingMeetingPagerPreview() {
    MoiveTheme {
        UpcomingMeetingPager(
            meetings = persistentListOf(
                UpcomingMeetingItemModel(
                    id = 1L,
                    title = "강남에서 만나자",
                    dateTime = "8월 29일 14:00",
                    location = "홍대입구역 2번 출구",
                    participantImageList = persistentListOf("", "", ""),
                    extraCount = 2,
                    dDayText = "D-5",
                ),
                UpcomingMeetingItemModel(
                    id = 2L,
                    title = "주말 맛집 모임",
                    dateTime = "8월 29일 14:00",
                    location = "홍대입구역 2번 출구",
                    participantImageList = persistentListOf("", ""),
                    extraCount = 1,
                    dDayText = "D-3",
                ),
            ),
            onMeetingClick = {},
            onAddMeetingClick = {},
            modifier = Modifier.padding(vertical = 20.dp),
        )
    }
}
