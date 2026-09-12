package com.moive.app.presentation.votestatus

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.component.topbar.MoiveSubTitleTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.extensions.openUrl
import com.moive.app.data.votingstatus.model.ScheduleVoteCandidateModel
import com.moive.app.presentation.common.component.placedetail.PlaceDetailContent
import com.moive.app.presentation.votestatus.VoteStatusContract.Step
import com.moive.app.presentation.votestatus.component.PlaceVoteSection
import com.moive.app.presentation.votestatus.component.ScheduleVoteSection
import kotlinx.collections.immutable.persistentListOf

@Composable
fun VoteStatusRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VoteStatusViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    BackHandler(enabled = uiState.step != Step.LIST) {
        viewModel.backToList()
    }

    VoteStatusScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onPlaceItemClick = viewModel::onPlaceItemClick,
        onDetailBackClick = viewModel::backToList,
        onKakaoMapClick = {
            val landingUrl = uiState.currentPlaceDetail.landingUrl
            if (landingUrl.isNotBlank()) {
                val opened = context.openUrl(landingUrl)
                viewModel.onKakaoMapRouteOpened(opened)
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun VoteStatusScreen(
    innerPadding: PaddingValues,
    uiState: VoteStatusContract.State,
    onBackClick: () -> Unit,
    onPlaceItemClick: (Long) -> Unit,
    onDetailBackClick: () -> Unit,
    onKakaoMapClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState.step) {
        Step.LIST -> Column(
            modifier = modifier
                .fillMaxSize()
                .background(colors.background.default02)
                .padding(innerPadding),
        ) {
            MoiveSubTitleTopBar(
                title = "투표 현황",
                onBackClick = onBackClick,
                backgroundColor = colors.background.default02,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentPadding = PaddingValues(top = 24.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(34.dp),
            ) {
                item {
                    ScheduleVoteSection(
                        isVoteSkipped = uiState.isScheduleVoteSkipped,
                        totalVoterCount = uiState.scheduleTotalVoterCount,
                        candidates = uiState.scheduleCandidates,
                        topVoterCount = uiState.scheduleTopVoterCount,
                        confirmedCandidate = uiState.confirmedScheduleCandidate,
                    )
                }

                item {
                    PlaceVoteSection(
                        totalVoterCount = uiState.placeTotalVoterCount,
                        candidates = uiState.placeCandidates,
                        topVoterCount = uiState.placeTopVoterCount,
                        onPlaceClick = onPlaceItemClick,
                    )
                }
            }
        }

        Step.DETAIL -> PlaceDetailContent(
            innerPadding = innerPadding,
            place = uiState.currentPlaceDetail,
            title = uiState.currentPlaceDetail.placeName,
            onBackClick = onDetailBackClick,
            onKakaoMapClick = onKakaoMapClick,
            showSelectButton = false,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VoteStatusScreenPreview() {
    MoiveTheme {
        VoteStatusScreen(
            innerPadding = PaddingValues(),
            uiState = VoteStatusContract.State(),
            onBackClick = {},
            onPlaceItemClick = {},
            onDetailBackClick = {},
            onKakaoMapClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VoteStatusScreenScheduleConfirmedPreview() {
    MoiveTheme {
        VoteStatusScreen(
            innerPadding = PaddingValues(),
            uiState = VoteStatusContract.State(
                isScheduleVoteSkipped = true,
                scheduleTotalVoterCount = null,
                scheduleCandidates = persistentListOf(
                    ScheduleVoteCandidateModel(meetingDate = "2026-09-12", meetingTime = "18:00", voterCount = null, isVotedByMe = true),
                ),
            ),
            onBackClick = {},
            onPlaceItemClick = {},
            onDetailBackClick = {},
            onKakaoMapClick = {},
        )
    }
}
