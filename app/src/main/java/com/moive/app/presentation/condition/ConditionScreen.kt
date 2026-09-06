package com.moive.app.presentation.condition

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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
    innerPadding: PaddingValues,
    navigateToMeetingDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ConditionViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.step != Step.INPUT) {
        viewModel.backToInputStep()
    }

    ConditionScreen(
        innerPadding = innerPadding,
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
    innerPadding: PaddingValues,
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
            modifier = modifier.padding(innerPadding),
        )

        Step.SEARCH -> ConditionPlaceSearchContent(
            onPlaceItemClick = onPlaceItemClick,
            modifier = modifier.padding(innerPadding),
        )

        Step.CONFIRM -> ConditionConfirmContent(
            selectedPlace = state.selectedPlace,
            onCompleteButtonClick = onCompleteButtonClick,
            modifier = modifier.padding(innerPadding),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConditionScreenPreview() {
    MoiveTheme {
        ConditionScreen(
            innerPadding = PaddingValues(),
            state = ConditionContract.State(),
            onSearchClick = {},
            onPlaceItemClick = {},
            onNextButtonClick = {},
            onCompleteButtonClick = {},
        )
    }
}
