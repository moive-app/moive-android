package com.moive.app.presentation.condition

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.presentation.condition.ConditionContract.Step
import com.moive.app.presentation.condition.component.ConditionConfirmContent
import com.moive.app.presentation.condition.component.ConditionInputContent
import com.moive.app.presentation.condition.component.ConditionPlaceSearchContent

@Composable
fun ConditionRoute(
    navigateToMeetingDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ConditionViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.step != Step.INPUT) {
        viewModel.backToInputStep()
    }

    ConditionScreen(
        state = uiState,
        onSearchClick = viewModel::onPlaceSearchBoxClick,
        onPlaceItemClick = viewModel::onPlaceItemClick,
        onNextButtonClick = viewModel::onNextButtonClick,
        onCompleteButtonClick = navigateToMeetingDetail,
        modifier = modifier,
    )
}

@Composable
private fun ConditionScreen(
    state: ConditionContract.State,
    onSearchClick: () -> Unit,
    onPlaceItemClick: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state.step) {
        Step.INPUT -> ConditionInputContent(
            selectedPlace = state.selectedPlace,
            onSearchClick = onSearchClick,
            onNextButtonClick = onNextButtonClick,
            modifier = modifier,
        )

        Step.SEARCH -> ConditionPlaceSearchContent(
            onPlaceItemClick = onPlaceItemClick,
            modifier = modifier,
        )

        Step.CONFIRM -> ConditionConfirmContent(
            selectedPlace = state.selectedPlace,
            onCompleteButtonClick = onCompleteButtonClick,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConditionScreenPreview() {
    MoiveTheme {
        ConditionScreen(
            state = ConditionContract.State(),
            onSearchClick = {},
            onPlaceItemClick = {},
            onNextButtonClick = {},
            onCompleteButtonClick = {},
        )
    }
}
