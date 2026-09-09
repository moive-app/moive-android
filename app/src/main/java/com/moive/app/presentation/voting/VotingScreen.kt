package com.moive.app.presentation.voting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.presentation.voting.VotingContract.Step
import com.moive.app.presentation.voting.component.PlaceDetailContent
import com.moive.app.presentation.voting.component.PlaceListContent
import kotlinx.collections.immutable.persistentSetOf

@Composable
fun VotingRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    navigateToVoteStatus: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VotingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.step != Step.RECOMMENDATION) {
        viewModel.backToPlaceList()
    }

    VotingScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onRegionPinClick = viewModel::onRegionPinClick,
        onPlaceItemClick = viewModel::onPlaceItemClick,
        onCheckboxClick = viewModel::onCheckboxClick,
        onBottomSheetDismiss = viewModel::onBottomSheetDismiss,
        onResetClick = viewModel::onResetSelectionClick,
        onBackClick = navigateBack,
        onDetailBackClick = viewModel::backToPlaceList,
        onKakaoMapClick = {},
        onSelectButtonClick = viewModel::onSelectButtonClick,
        onCompleteButtonClick = navigateToVoteStatus,
        modifier = modifier,
    )
}

@Composable
private fun VotingScreen(
    innerPadding: PaddingValues,
    uiState: VotingContract.State,
    onRegionPinClick: (String) -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onCheckboxClick: (Long) -> Unit,
    onBottomSheetDismiss: () -> Unit,
    onResetClick: () -> Unit,
    onBackClick: () -> Unit,
    onDetailBackClick: () -> Unit,
    onKakaoMapClick: () -> Unit,
    onSelectButtonClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState.step) {
        Step.RECOMMENDATION -> PlaceListContent(
            innerPadding = innerPadding,
            regionList = uiState.regionList,
            selectedRegionName = uiState.selectedRegionName,
            places = uiState.placeList,
            selectedPlaceIds = uiState.selectedPlaceList,
            isPlaceListVisible = uiState.isPlaceListVisible,
            onRegionPinClick = onRegionPinClick,
            onPlaceItemClick = onPlaceItemClick,
            onCheckboxClick = onCheckboxClick,
            onBottomSheetDismiss = onBottomSheetDismiss,
            onResetClick = onResetClick,
            onBackClick = onBackClick,
            onCompleteButtonClick = onCompleteButtonClick,
            modifier = modifier,
        )

        Step.DETAIL -> PlaceDetailContent(
            innerPadding = innerPadding,
            place = uiState.currentPlaceDetail,
            regionName = uiState.selectedRegionName ?: "추천 지역",
            onBackClick = onDetailBackClick,
            onKakaoMapClick = onKakaoMapClick,
            onSelectButtonClick = onSelectButtonClick,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VotingScreenPreview() {
    MoiveTheme {
        VotingScreen(
            innerPadding = PaddingValues(),
            uiState = VotingContract.State(
                isPlaceListVisible = true,
                selectedPlaceList = persistentSetOf(1L),
            ),
            onRegionPinClick = {},
            onPlaceItemClick = {},
            onCheckboxClick = {},
            onBottomSheetDismiss = {},
            onResetClick = {},
            onBackClick = {},
            onDetailBackClick = {},
            onKakaoMapClick = {},
            onSelectButtonClick = {},
            onCompleteButtonClick = {},
        )
    }
}
