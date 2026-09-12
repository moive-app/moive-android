package com.moive.app.presentation.voting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.extensions.openUrl
import com.moive.app.data.voting.model.RegionPinModel
import com.moive.app.presentation.common.component.placedetail.PlaceDetailContent
import com.moive.app.presentation.voting.VotingContract.Step
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
    val context = LocalContext.current

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
        onKakaoMapClick = {
            val landingUrl = uiState.currentPlaceDetail.landingUrl
            if (landingUrl.isNotBlank()) {
                val opened = context.openUrl(landingUrl)
                viewModel.onKakaoMapRouteOpened(opened)
            }
        },
        onSelectButtonClick = viewModel::onSelectButtonClick,
        onCompleteButtonClick = navigateToVoteStatus,
        modifier = modifier,
    )
}

@Composable
private fun VotingScreen(
    innerPadding: PaddingValues,
    uiState: VotingContract.State,
    onRegionPinClick: (RegionPinModel) -> Unit,
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
    Box(modifier = modifier.fillMaxSize()) {
        // 카카오맵 뷰를 step 전환마다 파괴/재생성하면 SDK가 새 엔진을 제대로 못 띄우는 문제가 있어,
        // Detail로 넘어가도 이 맵은 계속 마운트된 채로 두고 위에 PlaceDetailContent를 덮어씌운다.
        PlaceListContent(
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
        )

        if (uiState.step == Step.DETAIL) {
            PlaceDetailContent(
                innerPadding = innerPadding,
                place = uiState.currentPlaceDetail,
                title = uiState.currentPlaceDetail.areaName,
                onBackClick = onDetailBackClick,
                onKakaoMapClick = onKakaoMapClick,
                showSelectButton = true,
                onSelectButtonClick = onSelectButtonClick,
            )
        }
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
