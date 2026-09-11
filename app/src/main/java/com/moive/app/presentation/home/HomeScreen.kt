package com.moive.app.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.topbar.MoiveMainTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.common.component.TabChipList
import com.moive.app.presentation.common.component.MyMeetingCardItem
import com.moive.app.presentation.home.component.HomeEmptyMeetingList
import com.moive.app.presentation.home.component.ConfirmedMeetingPager
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeRoute(
    innerPadding: PaddingValues,
    navigateToMeetingList: () -> Unit,
    navigateToMeetingDetail: () -> Unit,
    navigateToMeetingCreation: () -> Unit,
    navigateToNotification: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.getHome(uiState.selectedTab)
        }
    }

    HomeScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onTabClick = viewModel::postMeetingFilter,
        onShowListClick = navigateToMeetingList,
        onMeetingClick = navigateToMeetingDetail,
        onAddMeetingClick = navigateToMeetingCreation,
        onNotificationClick = navigateToNotification,
        modifier = modifier,
    )
}

@Composable
private fun HomeScreen(
    innerPadding: PaddingValues,
    uiState: HomeContract.State,
    onTabClick: (String) -> Unit,
    onShowListClick: () -> Unit,
    onMeetingClick: () -> Unit,
    onAddMeetingClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.background.default00,
            )
            .padding(innerPadding),
    ) {
        MoiveMainTopBar(
            title = "MOIVE",
            isAlarmUnRead = uiState.isAlarmUnRead,
            onNotificationClick = onNotificationClick,
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 48.dp)
        ) {
            item {
                Text(
                    text = "${uiState.userName}님,\n오늘은 어디서 뭐 할까요?",
                    color = colors.text.default,
                    style = typography.title.xlB,
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp),
                )
            }

            item {
                if (uiState.upcomingMeetings.isEmpty()) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(320f / 220f)
                            .padding(horizontal = 20.dp)
                            .clip(
                                shape = RoundedCornerShape(radius.xxl)
                            ),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    ConfirmedMeetingPager(
                        meetings = uiState.upcomingMeetings,
                        onMeetingClick = { onMeetingClick() },
                        onAddMeetingClick = onAddMeetingClick,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(44.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "내 모임",
                        color = colors.text.default,
                        style = typography.title.lgB,
                        modifier = Modifier.weight(1f),
                    )

                    Text(
                        text = "전체보기",
                        color = colors.text.secondary,
                        style = typography.label.smR,
                        modifier = Modifier.noRippleClickable(onClick = onShowListClick),
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_chevron_right_16),
                        contentDescription = null,
                        tint = colors.icon.secondary,
                        modifier = Modifier.noRippleClickable(onClick = onShowListClick)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                TabChipList(
                    tabs = uiState.tabList,
                    selectedTab = uiState.selectedTab,
                    onTabClick = onTabClick,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            if (uiState.displayedMyMeetingList.isEmpty()) {
                item {
                    HomeEmptyMeetingList(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    )
                }
            } else {
                items(
                    items = uiState.displayedMyMeetingList,
                    key = { it.id },
                ) { meeting ->
                    MyMeetingCardItem(
                        title = meeting.title,
                        dateTime = meeting.dateTime ?: "일정 미정",
                        participantImageList = meeting.participantImageUrls,
                        extraCount = meeting.extraParticipantCount,
                        statusText = meeting.statusText,
                        statusLabelType = meeting.statusLabelType,
                        onCardClick = onMeetingClick ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 6.dp),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MoiveTheme {
        HomeScreen(
            innerPadding = PaddingValues(),
            uiState = HomeContract.State(),
            onTabClick = {},
            onShowListClick = {},
            onMeetingClick = {},
            onAddMeetingClick = {},
            onNotificationClick = {},
        )
    }
}

@Preview(showBackground = true, name = "Empty")
@Composable
private fun HomeScreenEmptyPreview() {
    MoiveTheme {
        HomeScreen(
            innerPadding = PaddingValues(),
            uiState = HomeContract.State(
                upcomingMeetings = persistentListOf(),
                myMeetingList = persistentListOf(),
            ),
            onTabClick = {},
            onShowListClick = {},
            onMeetingClick = {},
            onAddMeetingClick = {},
            onNotificationClick = {},
        )
    }
}
