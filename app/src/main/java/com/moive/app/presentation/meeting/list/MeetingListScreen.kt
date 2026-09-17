package com.moive.app.presentation.meeting.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.common.component.TabChipList
import com.moive.app.presentation.meeting.list.component.MeetingCardList

@Composable
fun MeetingListRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToMeetingDetail: (Long) -> Unit,
    navigateToMeetingComplete: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.getMeetingList()
        }
    }

    MeetingListScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onTabClick = viewModel::postMeetingFilter,
        onMeetingClick = { meetingId, statusLabelType ->
            if (statusLabelType == LabelType.COMPLETE) {
                navigateToMeetingComplete(meetingId)
            } else {
                navigateToMeetingDetail(meetingId)
            }
        },
        onLoadMore = { viewModel.getMeetingList(loadMore = true) },
        modifier = modifier,
    )
}

@Composable
private fun MeetingListScreen(
    innerPadding: PaddingValues,
    uiState: MeetingListContract.State,
    onBackClick: () -> Unit,
    onTabClick: (String) -> Unit,
    onMeetingClick: (Long, LabelType) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background.default00)
            .padding(innerPadding),
    ) {
        MoiveSubTitleTopBar(
            title = "전체보기",
            onBackClick = onBackClick,
        )

        TabChipList(
            tabs = uiState.tabList,
            selectedTab = uiState.selectedTab,
            onTabClick = onTabClick,
            modifier = Modifier.padding(horizontal = 20.dp),
        )


        when {
            uiState.meetingListUiState is MeetingListUiState.Loading && uiState.meetingList.isEmpty() -> Unit

            uiState.meetingList.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_character_empty_default),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "아직 참여 중인 모임이 없어요.",
                        color = colors.text.subtle,
                        style = typography.body.smNormalR,
                    )
                }
            }

            else -> {
                Spacer(modifier = Modifier.height(10.dp))

                MeetingCardList(
                    meetings = uiState.meetingList,
                    onMeetingClick = onMeetingClick,
                    isLoading = uiState.meetingListUiState is MeetingListUiState.Loading,
                    onLoadMore = onLoadMore,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 20.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingListScreenPreview() {
    MoiveTheme {
        MeetingListScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingListContract.State(),
            onBackClick = {},
            onTabClick = {},
            onMeetingClick = { _, _ -> },
            onLoadMore = {},
        )
    }
}
