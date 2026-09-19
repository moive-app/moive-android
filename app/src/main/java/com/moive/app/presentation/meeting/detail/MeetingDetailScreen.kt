package com.moive.app.presentation.meeting.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.tooltip.MoiveToolTip
import com.moive.app.core.designsystem.component.topbar.MoiveSubIconTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.shareText
import com.moive.app.data.meeting.mapper.MeetingStatus
import com.moive.app.data.meeting.model.ParticipantItemModel
import com.moive.app.presentation.common.component.ShadowButton
import com.moive.app.presentation.meeting.detail.component.LeaveMeetingDialog
import com.moive.app.presentation.meeting.detail.component.MeetingInfoRow
import com.moive.app.presentation.meeting.detail.component.NotParticipantDialog
import com.moive.app.presentation.meeting.detail.component.ParticipantItem
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MeetingDetailRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToCondition: (Boolean, String?, String?) -> Unit,
    navigateToVoting: () -> Unit,
    navigateToMeetingConfirmed: () -> Unit,
    navigateToMeetingComplete: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.getMeetingDetail()
        }
    }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    MeetingDetailContract.SideEffect.NavigateBack -> navigateBack()
                }
            }
        }
    }

    MeetingDetailScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onInviteFriendClick = {
            context.shareText("새로운 모임에 초대되었어요!🎉 아래 링크에서 모임을 확인해보세요.\n${uiState.inviteUrl}")
        },
        onActionButtonClick = {
            when (uiState.status) {
                MeetingStatus.CONDITION_INPUT -> navigateToCondition(
                    uiState.hasSchedule,
                    uiState.scheduledDate,
                    uiState.scheduledTime,
                )

                else -> Unit
            }
        },
        onPrimaryActionClick = {
            when (uiState.status) {
                MeetingStatus.CONDITION_INPUT -> navigateToVoting()
                MeetingStatus.VOTING -> navigateToVoting()
                MeetingStatus.CONFIRMED -> navigateToMeetingConfirmed()
                MeetingStatus.COMPLETED -> navigateToMeetingComplete()
            }
        },
        onMoreClick = viewModel::showLeaveMeetingDialog,
        onLeaveMeetingDialogDismiss = viewModel::dismissLeaveMeetingDialog,
        onLeaveMeetingClick = {
            viewModel.dismissLeaveMeetingDialog()
            viewModel.deleteMeeting()
        },
        onNotParticipantDialogClose = navigateBack,
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
    onPrimaryActionClick: () -> Unit,
    onLeaveMeetingDialogDismiss: () -> Unit,
    onLeaveMeetingClick: () -> Unit,
    onNotParticipantDialogClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val isContentScrollable by remember {
        derivedStateOf { lazyListState.canScrollForward || lazyListState.canScrollBackward }
    }
    val density = LocalDensity.current
    var toolTipHeight by remember { mutableStateOf(0.dp) }
    val isToolTipVisible = uiState.toolTipMessage.isNotEmpty()
    val toolTipReservedHeight =
        if (isToolTipVisible) toolTipHeight + 4.dp else 0.dp

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

        Box(
            modifier = Modifier.weight(1f),
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp + toolTipReservedHeight,
                ),
            ) {
                item {
                    MeetingInfoRow(
                        thumbnailRes = uiState.thumbnailType.toMeetingThumbnailRes(),
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

            if (isToolTipVisible) {
                MoiveToolTip(
                    text = uiState.toolTipMessage,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp)
                        .onSizeChanged { toolTipHeight = with(density) { it.height.toDp() } },
                )
            }
        }

        ShadowButton(
            text = uiState.primaryActionLabel,
            isEnabled = uiState.primaryActionEnabled,
            onClick = onPrimaryActionClick,
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

    if (uiState.isNotParticipantDialogVisible) {
        NotParticipantDialog(
            onCloseClick = onNotParticipantDialogClose,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingDetailScreenPreview() {
    MoiveTheme {
        MeetingDetailScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingDetailContract.State(
                status = MeetingStatus.CONDITION_INPUT,
                toolTipMessage = "아직 조건 입력 중이에요!",
                primaryActionLabel = "추천 장소 확인",
                primaryActionEnabled = false,
                participants = persistentListOf(
                    ParticipantItemModel(
                        id = 1L,
                        name = "사용자",
                        profileImageUrl = null,
                        statusLabel = "조건 입력 전",
                        isMe = true,
                        isDone = false,
                    ),
                    ParticipantItemModel(
                        id = 2L,
                        name = "참여자1",
                        profileImageUrl = null,
                        statusLabel = "조건 입력 완료",
                        isMe = false,
                        isDone = true,
                    ),
                ),
            ),
            onBackClick = {},
            onMoreClick = {},
            onInviteFriendClick = {},
            onActionButtonClick = {},
            onPrimaryActionClick = {},
            onNotParticipantDialogClose = {},
            onLeaveMeetingDialogDismiss = {},
            onLeaveMeetingClick = {},
        )
    }
}
