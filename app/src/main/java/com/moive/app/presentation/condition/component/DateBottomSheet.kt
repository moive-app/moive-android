package com.moive.app.presentation.condition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.component.bottomsheet.MoiveBottomSheet
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.chip.ChipType
import com.moive.app.core.designsystem.component.chip.MoiveChip
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.presentation.condition.CalendarDay
import com.moive.app.presentation.condition.ConditionContract
import com.moive.app.presentation.condition.DateTimeSelection

private val WEEKDAY_LABELS = listOf("일", "월", "화", "수", "목", "금", "토")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(
    state: ConditionContract.State,
    onDateBottomSheetDismiss: () -> Unit,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
    onDayClick: (Int) -> Unit,
    onTimeClick: (String) -> Unit,
    onSaveDateClick: () -> Unit,
    onNextDateClick: () -> Unit,
    onDateTimeChipClick: (DateTimeSelection) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    ),
) {
    MoiveBottomSheet(
        title = "일자 선택",
        modifier = modifier,
        bottomSheetState = bottomSheetState,
        onDismissRequest = onDateBottomSheetDismiss,
        content = {
            DateBottomSheetContent(
                uiState = state,
                onPrevMonthClick = onPrevMonthClick,
                onNextMonthClick = onNextMonthClick,
                onDayClick = onDayClick,
                onTimeClick = onTimeClick,
                onDateTimeChipClick = onDateTimeChipClick,
            )
        },
        buttonContent = {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                MoiveButton(
                    text = "저장",
                    type = MoiveButtonType.TERTIARY,
                    size = MoiveButtonSize.LARGE,
                    onClick = onSaveDateClick,
                    modifier = Modifier.weight(1f),
                )
                MoiveButton(
                    text = "다음",
                    type = MoiveButtonType.PRIMARY,
                    size = MoiveButtonSize.LARGE,
                    enabled = state.pendingDateTime != null,
                    onClick = onNextDateClick,
                    modifier = Modifier.weight(1f),
                )
            }
        },
    )
}

@Composable
private fun DateBottomSheetContent(
    uiState: ConditionContract.State,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
    onDayClick: (Int) -> Unit,
    onTimeClick: (String) -> Unit,
    onDateTimeChipClick: (DateTimeSelection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_chevron_left_20),
                contentDescription = null,
                tint = colors.icon.default,
                modifier = Modifier.noRippleClickable(onClick = onPrevMonthClick),
            )

            Text(
                text = "${uiState.calendarMonth}월",
                color = colors.text.default,
                style = typography.label.smM,
                modifier = Modifier.padding(horizontal = 4.dp),
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_chevron_right_20),
                contentDescription = null,
                tint = colors.icon.default,
                modifier = Modifier.noRippleClickable(onClick = onNextMonthClick),
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            WEEKDAY_LABELS.forEach { label ->
                Text(
                    text = label,
                    color = colors.text.default,
                    style = typography.label.smR,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "${uiState.calendarYear}년 ${uiState.calendarMonth}월",
            color = colors.text.default,
            style = typography.label.smM,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        uiState.calendarDays.chunked(7).forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                week.forEach { calendarDay ->
                    CalendarDayCell(
                        calendarDay = calendarDay,
                        isSelected = calendarDay.isCurrentMonth && calendarDay.day == uiState.pendingDay,
                        onClick = { onDayClick(calendarDay.day) },
                        modifier = Modifier.weight(1f),
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .background(
                    color = colors.fill.default06
                )
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = "모임 가능 시작 시간",
                color = colors.text.default,
                style = typography.title.xsSb,
                modifier = Modifier.padding(horizontal = 20.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(
                    items = uiState.timeOptions,
                    key = { it }
                ) { item ->
                    MoiveChip(
                        type = ChipType.TAB,
                        text = item,
                        isSelected = uiState.pendingTime == item,
                        onTabClick = { onTimeClick(item) },
                    )
                }
            }

            if (uiState.selectedDateTimes.isNotEmpty()) {
                Text(
                    text = "일시 확인",
                    color = colors.text.default,
                    style = typography.title.smSb,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 24.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    ),
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(
                        items = uiState.selectedDateTimes,
                        key = { "${it.year}-${it.month}-${it.day}-${it.time}" }
                    ) { item ->
                        DateTimeChip(
                            entry = item,
                            onClick = { onDateTimeChipClick(item) },
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun CalendarDayCell(
    calendarDay: CalendarDay,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .then(
                if (calendarDay.isCurrentMonth) {
                    Modifier.noRippleClickable(onClick = onClick)
                } else {
                    Modifier
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = calendarDay.day.toString(),
            color = when {
                isSelected -> colors.text.onBg
                calendarDay.isCurrentMonth -> colors.text.default
                else -> colors.text.tertiary
            },
            style = typography.label.mdM,
            modifier = if (isSelected) {
                Modifier
                    .background(
                        color = colors.primary.default,
                        shape = RoundedCornerShape(radius.circular),
                    )
                    .aspectRatio(1f)
                    .padding(vertical = 8.dp)
                    .wrapContentSize(Alignment.Center)
            } else {
                Modifier
            },
        )
    }
}

@Composable
private fun DateTimeChip(
    entry: DateTimeSelection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(
                width = 2.dp,
                color = colors.primary.sub01,
                shape = RoundedCornerShape(radius.sm),
            )
            .background(
                color = colors.primary.sub03,
                shape = RoundedCornerShape(radius.sm),
            )
            .noRippleClickable(onClick = onClick)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = entry.selectedDate,
            color = colors.primary.pressed,
            style = typography.label.xsM,
        )

        Text(
            text = entry.selectedTime,
            color = colors.primary.default,
            style = typography.label.xsR,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DateBottomSheetContentPreview() {
    MoiveTheme {
        DateBottomSheetContent(
            uiState = ConditionContract.State(),
            onPrevMonthClick = {},
            onNextMonthClick = {},
            onDayClick = {},
            onTimeClick = {},
            onDateTimeChipClick = {},
        )
    }
}
