package com.moive.app.presentation.condition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.chip.MoiveMultiSelectChipList
import com.moive.app.core.designsystem.component.chip.MoiveSingleSelectChipList
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.common.component.ShadowButton
import com.moive.app.presentation.condition.ConditionContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionInputContent(
    state: ConditionContract.State,
    onBackClick: () -> Unit,
    onDateBoxClick: () -> Unit,
    onDateBottomSheetDismiss: () -> Unit,
    onSaveDateClick: () -> Unit,
    onNextDateClick: () -> Unit,
    onPlaceBoxClick: () -> Unit,
    onTimeClick: (String) -> Unit,
    onPreferenceClick: (String) -> Unit,
    onBudgetClick: (String) -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MoiveSubTitleTopBar(
            title = "조건 입력",
            onBackClick = onBackClick,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = colors.background.default00,
                )
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
        ) {
            ConditionInputSectionTitle(
                text = "언제 만날까요?"
            )

            ConditionTextBox(
                placeholder = "가능한 날짜를 모두 선택해주세요",
                text = state.selectedDateText,
                trailingIcon = R.drawable.ic_calendar_20,
                onClick = onDateBoxClick,
            )

            Spacer(modifier = Modifier.height(32.dp))

            ConditionInputSectionTitle(
                text = "어디서 출발하나요?"
            )

            ConditionTextBox(
                placeholder = "위치를 설정해주세요",
                text = state.selectedPlaceName,
                leadingIcon = R.drawable.ic_search_20,
                onClick = onPlaceBoxClick,
            )

            Spacer(modifier = Modifier.height(32.dp))

            ConditionInputSectionTitle(
                text = "얼마나 이동 가능한가요?"
            )

            MoiveSingleSelectChipList(
                items = state.travelTimeList,
                selectedItem = state.selectedTravelTime,
                onItemClick = onTimeClick,
            )

            Spacer(modifier = Modifier.height(32.dp))

            ConditionInputSectionTitle(
                text = "취향을 선택해주세요"
            )

            state.preferenceCategories.forEach { category ->
                Text(
                    text = category.title,
                    color = colors.text.secondary,
                    style = typography.label.xsM,
                    modifier = Modifier.padding(bottom = 12.dp),
                )
                MoiveMultiSelectChipList(
                    items = category.items,
                    selectedItems = state.selectedPreferences,
                    onItemClick = onPreferenceClick,
                    modifier = Modifier.padding(bottom = 12.dp),
                )
            }
        }

        ShadowButton(
            text = "다음",
            isEnabled = state.isNextButtonEnabled,
            onClick = onNextButtonClick,
        )
    }

    if (state.isDateBottomSheetVisible) {
        DateBottomSheet(
            onDateBottomSheetDismiss = onDateBottomSheetDismiss,
            onSaveDateClick = onSaveDateClick,
            onNextDateClick = onNextDateClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConditionInputContentPreview() {
    MoiveTheme {
        ConditionInputContent(
            state = ConditionContract.State(),
            onBackClick = {},
            onDateBoxClick = {},
            onDateBottomSheetDismiss = {},
            onSaveDateClick = {},
            onNextDateClick = {},
            onPlaceBoxClick = {},
            onTimeClick = {},
            onPreferenceClick = {},
            onBudgetClick = {},
            onNextButtonClick = {},
        )
    }
}
