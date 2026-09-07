package com.moive.app.presentation.meeting.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.tooltip.MoiveToolTip
import com.moive.app.core.designsystem.component.topbar.MoiveSubIconTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.data.meeting.model.ParticipantItemModel
import com.moive.app.presentation.common.component.ShadowButton
import com.moive.app.presentation.meeting.detail.MeetingDetailContract.MeetingStatus
import com.moive.app.presentation.meeting.detail.component.LeaveMeetingDialog
import com.moive.app.presentation.meeting.detail.component.MeetingInfoRow
import com.moive.app.presentation.meeting.detail.component.ParticipantItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MeetingDetailRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToCondition: () -> Unit,
    navigateToVoting: () -> Unit,
    navigateToMeetingConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MeetingDetailScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onInviteFriendClick = {},
        onActionButtonClick = {
            when (uiState.status) {
                MeetingStatus.INPUTTING -> navigateToCondition()
                else -> Unit
            }
        },
        onBottomButtonClick = {
            when (uiState.status) {
                MeetingStatus.INPUTTING -> navigateToVoting()
                MeetingStatus.VOTING -> navigateToVoting()
                MeetingStatus.CONFIRMED -> navigateToMeetingConfirmed()
            }
        },
        onMoreClick = viewModel::showLeaveMeetingDialog,
        onLeaveMeetingDialogDismiss = viewModel::dismissLeaveMeetingDialog,
        onLeaveMeetingClick = {
            viewModel.dismissLeaveMeetingDialog()
            viewModel.deleteMeeting()
            navigateBack()
        },
        modifier = modifier,
    )
}

@Composable
private fun MeetingDetailScreen(
    innerPadding: PaddingValues,
    uiState: MeetingDetailContract.State,
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit,
    onInviteFriendClick: () -> Unit,
    onActionButtonClick: () -> Unit,
    onBottomButtonClick: () -> Unit,
    onLeaveMeetingDialogDismiss: () -> Unit,
    onLeaveMeetingClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val isContentScrollable by remember {
        derivedStateOf { lazyListState.canScrollForward || lazyListState.canScrollBackward }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background.default00)
            .padding(innerPadding),
    ) {
        MoiveSubIconTopBar(
            trailingIcon = R.drawable.ic_more_24,
            onLeadingIconClick = onBackClick,
            onTrailingIconClick = onMoreClick,
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        ) {
            item {
                MeetingInfoRow(
                    imageUrl = uiState.thumbnailUrl,
                    meetingName = uiState.meetingName,
                    meetingPurpose = uiState.meetingPurpose,
                )

                Spacer(modifier = Modifier.height(20.dp))

                MoiveButton(
                    text = "친구 초대",
                    icon = ImageVector.vectorResource(R.drawable.ic_mail_fill_20),
                    type = MoiveButtonType.TERTIARY,
                    size = MoiveButtonSize.MEDIUM,
                    onClick = onInviteFriendClick,
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_user_16),
                        contentDescription = null,
                        tint = colors.icon.tertiary,
                    )

                    Text(
                        text = "참여자 ${uiState.participants.size}명",
                        color = colors.text.tertiary,
                        style = typography.label.xsR,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            items(
                items = uiState.participants,
                key = { it.id }
            ) { participant ->
                ParticipantItem(
                    participant = participant,
                    status = uiState.status,
                    onActionButtonClick = onActionButtonClick,
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        if (uiState.status != MeetingStatus.INPUTTING || !uiState.isAllParticipantsDone) {
            MoiveToolTip(
                text = statusTooltipText(uiState.status),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .zIndex(1f)
                    .offset(y = 8.dp),
            )
        }

        ShadowButton(
            text = statusBottomButtonText(uiState.status),
            isEnabled = uiState.isAllParticipantsDone,
            onClick = onBottomButtonClick,
            showShadow = isContentScrollable,
        )
    }

    if (uiState.isLeaveMeetingDialogVisible) {
        LeaveMeetingDialog(
            meetingName = uiState.meetingName,
            onDismissRequest = onLeaveMeetingDialogDismiss,
            onLeaveClick = onLeaveMeetingClick,
        )
    }
}

@Preview(showBackground = true,)
@Composable
private fun MeetingDetailScreenPreview() {
    MoiveTheme {
        MeetingDetailScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingDetailContract.State(
                status = MeetingStatus.INPUTTING,
            ),
            onBackClick = {},
            onMoreClick = {},
            onInviteFriendClick = {},
            onActionButtonClick = {},
            onBottomButtonClick = {},
            onLeaveMeetingDialogDismiss = {},
            onLeaveMeetingClick = {},
        )
    }
}
