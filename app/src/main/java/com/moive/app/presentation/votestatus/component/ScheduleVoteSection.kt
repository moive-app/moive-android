package com.moive.app.presentation.votestatus.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.parseDate
import com.moive.app.data.votingstatus.model.ScheduleVoteCandidateModel
import com.moive.app.presentation.votestatus.voteRatio
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ScheduleVoteSection(
    isVoteSkipped: Boolean,
    totalVoterCount: Int?,
    candidates: ImmutableList<ScheduleVoteCandidateModel>,
    topVoterCount: Int,
    confirmedCandidate: ScheduleVoteCandidateModel,
    modifier: Modifier = Modifier,
) {
    VoteStatusSectionCard(
        title = "일정 투표",
        subTitle = "언제 만날까요?",
        modifier = modifier,
    ) {

        if (isVoteSkipped) {

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "일정이 이미 확정되었어요!",
                color = colors.secondary.default,
                style = typography.label.xsR,
            )

            Spacer(modifier = Modifier.height(12.dp))

            VoteOptionRow(
                text = "${confirmedCandidate.meetingDate.parseDate()} ${confirmedCandidate.meetingTime}",
                voteRatio = 1f,
                isTopVote = true,
                isVotedByMe = confirmedCandidate.isVotedByMe,
            )

            Spacer(modifier = Modifier.height(10.dp))

            VoterCountCaption(
                voterCount = null,
                suffix = "모두 같은 일정을 선택했어요",
            )

        } else {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                candidates.forEach { candidate ->
                    VoteOptionRow(
                        text = "${candidate.meetingDate.parseDate()} ${candidate.meetingTime}",
                        voteRatio = voteRatio(candidate.voterCount, totalVoterCount),
                        isTopVote = (candidate.voterCount ?: 0) == topVoterCount,
                        isVotedByMe = candidate.isVotedByMe,
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
}

@Preview(showBackground = true)
@Composable
private fun ScheduleVoteSectionPreview() {
    MoiveTheme {
        val candidates = persistentListOf(
            ScheduleVoteCandidateModel(meetingDate = "2026-09-12", meetingTime = "18:00", voterCount = 4, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "2026-09-08", meetingTime = "18:00", voterCount = 3, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "2026-09-10", meetingTime = "18:00", voterCount = 3, isVotedByMe = false),
        )
        ScheduleVoteSection(
            isVoteSkipped = false,
            totalVoterCount = 5,
            candidates = candidates,
            topVoterCount = candidates.maxOf { it.voterCount ?: 0 },
            confirmedCandidate = candidates.first(),
        )
    }
}

@Preview(showBackground = true, name = "일정 확정됨")
@Composable
private fun ScheduleVoteSectionConfirmedPreview() {
    MoiveTheme {
        val candidates = persistentListOf(
            ScheduleVoteCandidateModel(meetingDate = "2026-09-12", meetingTime = "18:00", voterCount = null, isVotedByMe = true),
        )
        ScheduleVoteSection(
            isVoteSkipped = true,
            totalVoterCount = null,
            candidates = candidates,
            topVoterCount = 0,
            confirmedCandidate = candidates.first(),
        )
    }
}
