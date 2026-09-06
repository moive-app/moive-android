package com.moive.app.presentation.meeting.create

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.presentation.meeting.create.MeetingCreationContract.Step
import com.moive.app.presentation.meeting.create.component.MeetingCreationContent
import com.moive.app.presentation.meeting.create.component.MeetingInfoConfirmContent

@Composable
fun MeetingCreationRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToMeetingDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingCreationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.step != Step.CREATE) {
        viewModel.backToCreateStep()
    }

    MeetingCreationScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onToggleScheduleConfirmed = viewModel::toggleScheduleConfirmed,
        onTogglePurpose = viewModel::toggleMeetingPurpose,
        onNextButtonClick = {
            viewModel.trimMeetingName()
            viewModel.moveToConfirmStep()
        },
        onConfirmBackClick = viewModel::backToCreateStep,
        onConfirmButtonClick = navigateToMeetingDetail,
        modifier = modifier,
    )
}

@Composable
private fun MeetingCreationScreen(
    innerPadding: PaddingValues,
    uiState: MeetingCreationContract.State,
    onBackClick: () -> Unit,
    onToggleScheduleConfirmed: (String) -> Unit,
    onTogglePurpose: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    onConfirmBackClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState.step) {
        Step.CREATE -> MeetingCreationContent(
            innerPadding = innerPadding,
            uiState = uiState,
            onBackClick = onBackClick,
            onToggleScheduleConfirmed = onToggleScheduleConfirmed,
            onTogglePurpose = onTogglePurpose,
            onNextButtonClick = onNextButtonClick,
            modifier = modifier,
        )

        Step.CONFIRM -> MeetingInfoConfirmContent(
            innerPadding = innerPadding,
            meetingName = uiState.meetingName.text.toString(),
            schedule = uiState.meetingSchedule.text.toString(),
            purpose = uiState.selectedMeetingPurpose,
            onBackClick = onConfirmBackClick,
            onConfirmButtonClick = onConfirmButtonClick,
            modifier = modifier,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MeetingCreationScreenPreview() {
    MoiveTheme {
        var uiState by remember { mutableStateOf(MeetingCreationContract.State()) }

        MeetingCreationScreen(
            innerPadding = PaddingValues(),
            uiState = uiState,
            onBackClick = {},
            onToggleScheduleConfirmed = { uiState = uiState.copy(selectedScheduleConfirmed = it) },
            onTogglePurpose = { uiState = uiState.copy(selectedMeetingPurpose = it) },
            onNextButtonClick = { uiState = uiState.copy(step = Step.CONFIRM) },
            onConfirmBackClick = { uiState = uiState.copy(step = Step.CREATE) },
            onConfirmButtonClick = {},
        )
    }
}
