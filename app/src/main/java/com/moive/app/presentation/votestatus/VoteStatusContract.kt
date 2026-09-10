package com.moive.app.presentation.votestatus

import androidx.compose.runtime.Immutable
import com.moive.app.data.votingstatus.model.PlaceVoteCandidateModel
import com.moive.app.data.votingstatus.model.ScheduleVoteCandidateModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface VoteStatusContract {
    @Immutable
    data class State(
        val isScheduleVoteSkipped: Boolean = false,
        val scheduleTotalVoterCount: Int? = 5,
        val scheduleCandidates: ImmutableList<ScheduleVoteCandidateModel> = persistentListOf(
            ScheduleVoteCandidateModel(meetingDate = "2026-09-12", meetingTime = "18:00", voterCount = 4, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "2026-09-08", meetingTime = "18:00", voterCount = 3, isVotedByMe = true),
            ScheduleVoteCandidateModel(meetingDate = "2026-09-10", meetingTime = "18:00", voterCount = 3, isVotedByMe = false),
        ),
        val placeTotalVoterCount: Int = 4,
        val placeCandidates: ImmutableList<PlaceVoteCandidateModel> = persistentListOf(
            PlaceVoteCandidateModel(placeName = "OOO 맛집", voterCount = 4, isVotedByMe = true),
            PlaceVoteCandidateModel(placeName = "XXX 카페", voterCount = 3, isVotedByMe = true),
            PlaceVoteCandidateModel(placeName = "ΔΔΔ 이자카야", voterCount = 2, isVotedByMe = false),
        ),
    ) {
        val scheduleTopVoterCount: Int
            get() = scheduleCandidates.maxOfOrNull { it.voterCount ?: 0 } ?: 0

        val confirmedScheduleCandidate: ScheduleVoteCandidateModel
            get() = scheduleCandidates.first()

        val placeTopVoterCount: Int
            get() = placeCandidates.maxOfOrNull { it.voterCount } ?: 0
    }
}
