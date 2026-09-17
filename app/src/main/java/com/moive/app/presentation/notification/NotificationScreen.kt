package com.moive.app.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.OnBottomReached
import com.moive.app.core.extensions.isNotificationEnabled
import com.moive.app.core.extensions.navigateToAppNotificationSettings
import com.moive.app.data.notification.model.NotificationItemModel
import com.moive.app.presentation.notification.component.NotificationListItem
import com.moive.app.presentation.notification.component.NotificationSettingButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun NotificationRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToMeetingDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.onNotificationPermissionChanged(context.isNotificationEnabled())
        }
    }

    NotificationScreen(
        innerPadding = innerPadding,
        notificationList = uiState.notifications,
        isLoading = uiState.notificationUiState is NotificationUiState.Loading,
        isNotificationPermissionGranted = uiState.isNotificationPermissionGranted,
        onBackClick = navigateBack,
        onNotificationItemClick = navigateToMeetingDetail,
        onNotificationRead = viewModel::patchNotificationReadStatus,
        onNotificationSettingClick = { context.navigateToAppNotificationSettings() },
        onLoadMore = { viewModel.getNotificationList(loadMore = true) },
        modifier = modifier,
    )
}

@Composable
private fun NotificationScreen(
    innerPadding: PaddingValues,
    notificationList: ImmutableList<NotificationItemModel>,
    isLoading: Boolean,
    isNotificationPermissionGranted: Boolean,
    onBackClick: () -> Unit,
    onNotificationItemClick: (Long) -> Unit,
    onNotificationRead: (Long) -> Unit,
    onNotificationSettingClick: () -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background.default02)
            .padding(innerPadding),
    ) {
        MoiveSubTitleTopBar(
            title = "알림함",
            onBackClick = onBackClick,
            backgroundColor = colors.background.default02,
        )

        if (notificationList.isEmpty()) {
            NotificationSettingButton(
                isNotificationPermissionGranted = isNotificationPermissionGranted,
                onSettingClick = onNotificationSettingClick,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 5.dp)
            )

            if (!isLoading) {
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
                        text = "새로운 알림이 없어요.",
                        color = colors.text.tertiary,
                        style = typography.body.smNormalR,
                    )
                }
            }
        } else {
            val listState = rememberLazyListState()

            listState.OnBottomReached(
                threshold = 3,
                isLoading = isLoading,
                onLoadMore = onLoadMore,
            )

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 23.dp),
            ) {
                item {
                    NotificationSettingButton(
                        isNotificationPermissionGranted = isNotificationPermissionGranted,
                        onSettingClick = onNotificationSettingClick,
                        modifier = Modifier.padding(top = 24.dp, bottom = 28.dp),
                    )
                }

                items(
                    items = notificationList,
                    key = { it.id },
                ) { item ->
                    NotificationListItem(
                        item = item,
                        onItemClick = {
                            onNotificationRead(item.id)
                            item.meetingId?.let(onNotificationItemClick)
                        },
                    )

                    Spacer(modifier.height(12.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationScreenListPreview() {
    MoiveTheme {
        NotificationScreen(
            innerPadding = PaddingValues(),
            notificationList = persistentListOf(
                NotificationItemModel(
                    id = 1L,
                    type = "COND_INPUT",
                    meetingId = 1L,
                    title = "조건 입력을 완료해주세요",
                    description = "'주말 맛집 모임'의 조건을 아직 입력하지 않았어요.",
                    time = "10분 전",
                    isRead = false,
                ),
                NotificationItemModel(
                    id = 2L,
                    type = "APP_UPDATE",
                    meetingId = null,
                    title = "업데이트",
                    description = "새로운 업데이트가 있어요.",
                    time = "1시간 전",
                    isRead = true,
                ),
            ),
            isLoading = false,
            isNotificationPermissionGranted = true,
            onBackClick = {},
            onNotificationItemClick = {},
            onNotificationRead = {},
            onNotificationSettingClick = {},
            onLoadMore = {},
        )
    }
}
