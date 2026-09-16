package com.moive.app.presentation.condition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.common.component.ConfirmInfoRow
import com.moive.app.presentation.common.component.ShadowButton
import com.moive.app.presentation.condition.ConditionContract
import com.moive.app.presentation.condition.ConditionUiState
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ConditionConfirmContent(
    state: ConditionContract.State,
    onBackClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
) {
    val lazyListState = rememberLazyListState()
    val isContentScrollable by remember {
        derivedStateOf { lazyListState.canScrollForward || lazyListState.canScrollBackward }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.fill.default08,
            )
            .padding(innerPadding),
    ) {
        MoiveSubTitleTopBar(
            title = "조건 확인",
            onBackClick = onBackClick,
            hasIcon = false,
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 44.dp,
            ),
        ) {
            item {
                Text(
                    text = "입력한 조건을 확인해주세요",
                    color = colors.text.default,
                    style = typography.title.smSb,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colors.stroke.default03,
                            shape = RoundedCornerShape(radius.lg),
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    ConfirmInfoRow(
                        label = "모임 일시 후보",
                        value = state.selectedDateText ?: ""
                    )

                    ConfirmInfoRow(
                        label = "출발 위치",
                        value = state.selectedPlaceText ?: ""
                    )


                    ConfirmInfoRow(
                        label = "선호 이동 거리",
                        value = state.selectedTravelTime ?: ""
                    )


                    ConfirmInfoRow(
                        label = "취향",
                        value = state.selectedPreferences.joinToString(separator = ", "),
                    )
                }
            }
        }

        ShadowButton(
            text = "완료",
            isEnabled = true,
            onClick = onConfirmButtonClick,
            showShadow = isContentScrollable,
            isLoading = state.conditionUiState is ConditionUiState.Loading,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConditionConfirmContentPreview() {
    MoiveTheme {
        ConditionConfirmContent(
            state = ConditionContract.State(
                selectedPlaceId = 1L,
                selectedTravelTime = "30분 이내",
                selectedPreferences = persistentListOf("맛집"),
            ),
            onBackClick = {},
            onConfirmButtonClick = {},
        )
    }
}
