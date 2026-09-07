package com.moive.app.presentation.meeting.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.presentation.common.component.TabChipList
import com.moive.app.presentation.meeting.list.component.MeetingCardList

@Composable
fun MeetingListRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToMeetingDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MeetingListScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onTabClick = viewModel::postMeetingFilter,
        onMeetingClick = { navigateToMeetingDetail() },
        modifier = modifier,
    )
}

@Composable
private fun MeetingListScreen(
    innerPadding: PaddingValues,
    uiState: MeetingListContract.State,
    onBackClick: () -> Unit,
    onTabClick: (String) -> Unit,
    onMeetingClick: (Long) -> Unit,
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
            modifier = Modifier
                .padding(bottom = 4.dp)
                .padding(horizontal = 20.dp),
        )

        MeetingCardList(
            meetings = uiState.meetingList,
            onMeetingClick = onMeetingClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp),
        )
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
            onMeetingClick = {},
        )
    }
}
