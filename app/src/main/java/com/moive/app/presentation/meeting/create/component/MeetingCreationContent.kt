package com.moive.app.presentation.meeting.create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.chip.MoiveSingleSelectChipList
import com.moive.app.core.designsystem.component.textfield.MoiveInputTextField
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.common.component.ShadowButton
import com.moive.app.presentation.meeting.create.MeetingCreationContract

@Composable
fun MeetingCreationContent(
    uiState: MeetingCreationContract.State,
    onBackClick: () -> Unit,
    onToggleScheduleConfirmed: (String) -> Unit,
    onTogglePurpose: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()
    val isContentScrollable by remember {
        derivedStateOf { lazyListState.canScrollForward || lazyListState.canScrollBackward }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.background.default00
            ),
    ) {
        MoiveSubTitleTopBar(
            title = "모임 생성",
            onBackClick = onBackClick,
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 24.dp,
            ),
        ) {
            item {
                Text(
                    text = "모임을 만들어볼까요?",
                    color = colors.text.default,
                    style = typography.title.lgB,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "기본 정보와 모임 목적을 입력해주세요.",
                    color = colors.text.secondary,
                    style = typography.body.smNormalR,
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "모임 이름",
                    color = colors.text.default,
                    style = typography.title.smSb,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                MoiveInputTextField(
                    state = uiState.meetingName,
                    placeholder = "모임 이름을 입력해주세요.",
                    onKeyboardAction = {
                        focusManager.clearFocus()
                    },
                    isError = uiState.isMeetingNameInvalid,
                )

                if (uiState.isMeetingNameInvalid) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "한영 10자 이내, 공백 및 특수문자 불가",
                        color = colors.status.error.default,
                        style = typography.label.xsR,
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Text(
                    text = "일정이 확정됐나요?",
                    color = colors.text.default,
                    style = typography.title.smSb,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                MoiveSingleSelectChipList(
                    items = uiState.scheduleConfirmed,
                    selectedItem = uiState.selectedScheduleConfirmed,
                    onItemClick = onToggleScheduleConfirmed,
                    modifier = Modifier.fillMaxWidth(),
                )

                if (uiState.selectedScheduleConfirmed == "네") {
                    Spacer(modifier = Modifier.height(12.dp))

                    MoiveInputTextField(
                        state = uiState.meetingSchedule,
                        placeholder = "예) 8월 29일 14:00",
                        onKeyboardAction = {
                            focusManager.clearFocus()
                        },
                    )

                    if (uiState.isMeetingScheduleFormatInvalid) {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "날짜와 시간을 올바른 형식으로 입력해주세요.",
                            color = colors.status.error.default,
                            style = typography.label.xsR,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "무엇을 할까요?",
                    color = colors.text.default,
                    style = typography.title.smSb,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                MoiveSingleSelectChipList(
                    items = uiState.meetingPurpose,
                    selectedItem = uiState.selectedMeetingPurpose,
                    onItemClick = onTogglePurpose,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        ShadowButton(
            text = "다음",
            isEnabled = uiState.isNextButtonEnabled,
            onClick = onNextButtonClick,
            showShadow = isContentScrollable,
        )
    }
}
