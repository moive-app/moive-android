package com.moive.app.presentation.voting

import androidx.activity.compose.BackHandler
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
    navigateToVoteStatus: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VotingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = uiState.step != Step.RECOMMENDATION) {
        viewModel.backToPlaceList()
    }

    VotingScreen(
        state = uiState,
        onRegionPinClick = viewModel::onRegionPinClick,
        onPlaceItemClick = viewModel::onPlaceItemClick,
        onCheckboxClick = viewModel::onCheckboxClick,
        onSelectButtonClick = viewModel::onSelectButtonClick,
        onCompleteButtonClick = navigateToVoteStatus,
        modifier = modifier,
    )
}

@Composable
private fun VotingScreen(
    state: VotingContract.State,
    onRegionPinClick: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onCheckboxClick: (Long) -> Unit,
    onSelectButtonClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state.step) {
        Step.RECOMMENDATION -> PlaceListContent(
            locationX = state.locationX,
            locationY = state.locationY,
            regionName = state.regionName,
            places = state.placeList,
            selectedPlaceIds = state.selectedPlaceList,
            isPlaceListVisible = state.isPlaceListVisible,
            onRegionPinClick = onRegionPinClick,
            onPlaceItemClick = onPlaceItemClick,
            onCheckboxClick = onCheckboxClick,
            onCompleteButtonClick = onCompleteButtonClick,
            modifier = modifier,
        )

        Step.DETAIL -> PlaceDetailContent(
            placeId = state.currentPlaceId,
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
            state = VotingContract.State(
                isPlaceListVisible = true,
                selectedPlaceList = persistentSetOf(1L),
            ),
            onRegionPinClick = {},
            onPlaceItemClick = {},
            onCheckboxClick = {},
            onSelectButtonClick = {},
            onCompleteButtonClick = {},
        )
    }
}
