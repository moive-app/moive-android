package com.moive.app.presentation.condition

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
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
    navigateBack: () -> Unit,
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
        onBackClick = navigateBack,
        onDateBoxClick = viewModel::onDateBoxClick,
        onDateBottomSheetDismiss = viewModel::dismissDateBottomSheet,
        onCalendarPrevMonthClick = viewModel::onCalendarPrevMonthClick,
        onCalendarNextMonthClick = viewModel::onCalendarNextMonthClick,
        onCalendarDayClick = viewModel::onCalendarDayClick,
        onCalendarTimeClick = viewModel::onCalendarTimeClick,
        onSaveDateClick = viewModel::onDateSaveClick,
        onNextDateClick = viewModel::onDateNextClick,
        onPlaceBoxClick = viewModel::onPlaceSearchBoxClick,
        onPlaceBackClick = viewModel::backToInputStep,
        onPlaceSearchSubmit = viewModel::postPlaceSearch,
        onPlaceItemClick = viewModel::onPlaceItemClick,
        onTravelTimeClick = viewModel::onTravelTimeClick,
        onPreferenceClick = viewModel::onPreferenceClick,
        onNextButtonClick = viewModel::onNextButtonClick,
        onConfirmButtonClick = navigateToMeetingDetail,
        modifier = modifier,
    )
}

@Composable
private fun ConditionScreen(
    innerPadding: PaddingValues,
    state: ConditionContract.State,
    onBackClick: () -> Unit,
    onDateBoxClick: () -> Unit,
    onDateBottomSheetDismiss: () -> Unit,
    onCalendarPrevMonthClick: () -> Unit,
    onCalendarNextMonthClick: () -> Unit,
    onCalendarDayClick: (Int) -> Unit,
    onCalendarTimeClick: (String) -> Unit,
    onSaveDateClick: () -> Unit,
    onNextDateClick: () -> Unit,
    onPlaceBoxClick: () -> Unit,
    onPlaceBackClick: () -> Unit,
    onPlaceSearchSubmit: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onTravelTimeClick: (String) -> Unit,
    onPreferenceClick: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state.step) {
        Step.INPUT -> ConditionInputContent(
            state = state,
            onBackClick = onBackClick,
            onDateBoxClick = onDateBoxClick,
            onDateBottomSheetDismiss = onDateBottomSheetDismiss,
            onCalendarPrevMonthClick = onCalendarPrevMonthClick,
            onCalendarNextMonthClick = onCalendarNextMonthClick,
            onCalendarDayClick = onCalendarDayClick,
            onCalendarTimeClick = onCalendarTimeClick,
            onSaveDateClick = onSaveDateClick,
            onNextDateClick = onNextDateClick,
            onPlaceBoxClick = onPlaceBoxClick,
            onTimeClick = onTravelTimeClick,
            onPreferenceClick = onPreferenceClick,
            onNextButtonClick = onNextButtonClick,
            modifier = modifier,
            innerPadding = innerPadding,
        )

        Step.SEARCH -> ConditionPlaceSearchContent(
            searchState = state.searchFieldState,
            placeList = state.placeList,
            onBackClick = onPlaceBackClick,
            onSearchClick = onPlaceSearchSubmit,
            onPlaceItemClick = onPlaceItemClick,
            modifier = modifier,
            innerPadding = innerPadding,
        )

        Step.CONFIRM -> ConditionConfirmContent(
            state = state,
            onBackClick = onPlaceBackClick,
            onConfirmButtonClick = onConfirmButtonClick,
            modifier = modifier,
            innerPadding = innerPadding,
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
            onBackClick = {},
            onDateBoxClick = {},
            onDateBottomSheetDismiss = {},
            onCalendarPrevMonthClick = {},
            onCalendarNextMonthClick = {},
            onCalendarDayClick = {},
            onCalendarTimeClick = {},
            onSaveDateClick = {},
            onNextDateClick = {},
            onPlaceBoxClick = {},
            onPlaceBackClick = {},
            onPlaceSearchSubmit = {},
            onPlaceItemClick = {},
            onTravelTimeClick = {},
            onPreferenceClick = {},
            onNextButtonClick = {},
            onConfirmButtonClick = {},
        )
    }
}
