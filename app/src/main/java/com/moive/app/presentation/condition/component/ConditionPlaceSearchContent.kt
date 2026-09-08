package com.moive.app.presentation.condition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.chip.LabelType
import com.moive.app.core.designsystem.component.chip.MoiveLabelChip
import com.moive.app.core.designsystem.component.textfield.MoiveSearchTextField
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable
import com.moive.app.data.condition.model.PlaceSearchItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ConditionPlaceSearchContent(
    searchState: TextFieldState,
    placeList: ImmutableList<PlaceSearchItemModel>,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colors.fill.default08
            ),
    ) {
        MoiveSubTitleTopBar(
            title = "위치",
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        MoiveSearchTextField(
            state = searchState,
            placeholder = "위치를 검색해주세요",
            onSearch = onSearchClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (placeList.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = colors.fill.default06
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "검색결과가 없습니다",
                    color = colors.text.default,
                    style = typography.label.smM,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "도로명, 지번, 건물명, 아파트명으로\n다시 검색해주세요",
                    color = colors.text.tertiary,
                    style = typography.label.xsM,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(20.dp)
            ) {
                itemsIndexed(
                    items = placeList,
                    key = { _, place -> place.id }
                ) { index, place ->
                    PlaceSearchItem(
                        place = place,
                        onItemClick = { onPlaceItemClick(place.id) },
                    )

                    if (index != placeList.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = colors.stroke.default04,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaceSearchItem(
    place: PlaceSearchItemModel,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = { onItemClick(place.id) })
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = place.name,
            color = colors.text.default,
            style = typography.label.mdM,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MoiveLabelChip(
                style = LabelType.CATEGORY.getStyle(),
                text = "도로명",
            )

            Text(
                text = place.address,
                color = colors.text.tertiary,
                style = typography.label.xsM,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConditionPlaceSearchContentPreview() {
    MoiveTheme {
        ConditionPlaceSearchContent(
            searchState = rememberTextFieldState(initialText = ""),
            placeList = persistentListOf(
                PlaceSearchItemModel(
                    id = 1L,
                    name = "꽃뫼버들마을금강KCC아파트",
                    address = "경기 수원시 팔달구 정자천로 32번길 27",
                ),
                PlaceSearchItemModel(
                    id = 2L,
                    name = "꽃뫼버들마을금강KCC아파트",
                    address = "경기 수원시 팔달구 정자천로 32번길 27",
                ),
                PlaceSearchItemModel(
                    id = 3L,
                    name = "꽃뫼버들마을금강KCC아파트",
                    address = "경기 수원시 팔달구 정자천로 32번길 27",
                ),
            ),
            onSearchClick = {},
            onBackClick = {},
            onPlaceItemClick = {},
        )
    }
}
