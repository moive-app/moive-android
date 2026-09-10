package com.moive.app.presentation.votestatus.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.data.votingstatus.model.PlaceVoteCandidateModel
import com.moive.app.presentation.votestatus.voteRatio
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val TOP_RANK_COUNT = 3

@Composable
fun PlaceVoteSection(
    totalVoterCount: Int,
    candidates: ImmutableList<PlaceVoteCandidateModel>,
    topVoterCount: Int,
    onPlaceClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val top3 = candidates.take(TOP_RANK_COUNT)

    VoteStatusSectionCard(
        title = "장소 투표",
        subTitle = "어디서 만날까요?",
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            top3.forEach { candidate ->
                VoteOptionRow(
                    text = candidate.placeName,
                    voteRatio = voteRatio(candidate.voterCount, totalVoterCount),
                    isTopVote = candidate.voterCount == topVoterCount,
                    isVotedByMe = candidate.isVotedByMe,
                    onClick = { onPlaceClick(candidate.id) },
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        VoterCountCaption(
            voterCount = topVoterCount,
            suffix = "이 투표했어요",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceVoteSectionPreview() {
    MoiveTheme {
        val candidates = persistentListOf(
            PlaceVoteCandidateModel(id = 1L, placeName = "OOO 맛집", voterCount = 4, isVotedByMe = true),
            PlaceVoteCandidateModel(id = 2L, placeName = "XXX 카페", voterCount = 3, isVotedByMe = true),
            PlaceVoteCandidateModel(id = 3L, placeName = "ΔΔΔ 이자카야", voterCount = 2, isVotedByMe = false),
        )
        PlaceVoteSection(
            totalVoterCount = 4,
            candidates = candidates,
            topVoterCount = candidates.maxOf { it.voterCount },
            onPlaceClick = {},
        )
    }
}
