package com.moive.app.presentation.meeting.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.chip.MoiveSingleSelectChipList
import com.moive.app.core.designsystem.component.textfield.MoiveInputTextField
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun MeetingCreationRoute(
    navigateToMeetingInfoConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingCreationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MeetingCreationScreen(
        uiState = uiState,
        onBackClick = {},
        onToggleScheduleConfirmed = viewModel::toggleScheduleConfirmed,
        onTogglePurpose = viewModel::toggleMeetingPurpose,
        onNextButtonClick = navigateToMeetingInfoConfirm,
        modifier = modifier,
    )
}

@Composable
private fun MeetingCreationScreen(
    uiState: MeetingCreationContract.State,
    onBackClick: () -> Unit,
    onToggleScheduleConfirmed: (String) -> Unit,
    onTogglePurpose: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    onKeyboardAction = {
                        focusManager.moveFocus(focusDirection = FocusDirection.Down)
                    },
                )

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

        MoiveButton(
            text = "다음",
            size = MoiveButtonSize.LARGE,
            type = MoiveButtonType.PRIMARY,
            onClick = onNextButtonClick,
            enabled = uiState.isNextButtonEnabled,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MeetingCreationScreenPreview() {
    MoiveTheme {
        var uiState by remember { mutableStateOf(MeetingCreationContract.State()) }

        MeetingCreationScreen(
            uiState = uiState,
            onBackClick = {},
            onToggleScheduleConfirmed = { uiState = uiState.copy(selectedScheduleConfirmed = it) },
            onTogglePurpose = { uiState = uiState.copy(selectedMeetingPurpose = it) },
            onNextButtonClick = {},
        )
    }
}
