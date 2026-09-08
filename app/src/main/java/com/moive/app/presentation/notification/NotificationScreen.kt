package com.moive.app.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.extensions.isNotificationEnabled
import com.moive.app.core.extensions.navigateToAppNotificationSettings
import com.moive.app.data.notification.model.NotificationItemModel
import com.moive.app.presentation.notification.component.EmptyNotificationContent
import com.moive.app.presentation.notification.component.NotificationListItem
import com.moive.app.presentation.notification.component.NotificationSettingButton
import kotlinx.collections.immutable.ImmutableList

@Composable
fun NotificationRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToMeetingDetail: () -> Unit,
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
        notifications = uiState.notifications,
        isNotificationPermissionGranted = uiState.isNotificationPermissionGranted,
        onBackClick = navigateBack,
        onNotificationItemClick = { navigateToMeetingDetail() },
        onNotificationSettingClick = { context.navigateToAppNotificationSettings() },
        modifier = modifier,
    )
}

@Composable
private fun NotificationScreen(
    innerPadding: PaddingValues,
    notifications: ImmutableList<NotificationItemModel>,
    isNotificationPermissionGranted: Boolean,
    onBackClick: () -> Unit,
    onNotificationItemClick: (Long) -> Unit,
    onNotificationSettingClick: () -> Unit,
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

        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 20.dp),
        ) {
            item {
                NotificationSettingButton(
                    isNotificationPermissionGranted = isNotificationPermissionGranted,
                    onSettingClick = onNotificationSettingClick,
                )
            }

            if (notifications.isEmpty()) {
                item {
                    EmptyNotificationContent(
                        modifier = Modifier.fillParentMaxHeight(),
                    )
                }
            } else {
                item {
                    Spacer(modifier = modifier.height(28.dp))
                }

                items(
                    items = notifications,
                    key = { it.id },
                ) { item ->
                    NotificationListItem(
                        item = item,
                        onItemClick = { onNotificationItemClick(item.id) },
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
            notifications = NotificationContract.State().notifications,
            isNotificationPermissionGranted = true,
            onBackClick = {},
            onNotificationItemClick = {},
            onNotificationSettingClick = {},
        )
    }
}
