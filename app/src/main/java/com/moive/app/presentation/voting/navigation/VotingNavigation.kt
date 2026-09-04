package com.moive.app.presentation.voting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.detail.navigation.MeetingDetail
import com.moive.app.presentation.voting.VotingRoute
import com.moive.app.presentation.votestatus.navigation.navigateToVoteStatus
import kotlinx.serialization.Serializable

fun NavController.navigateToVoting(
    navOptions: NavOptions? = null
) = navigate(Voting, navOptions)

fun NavGraphBuilder.votingGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Voting> {
        VotingRoute(
            navigateToVoteStatus = {
                navController.navigateToVoteStatus(
                    navOptions = navOptions {
                        popUpTo<MeetingDetail> {
                            inclusive = false
                        }
                    },
                )
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Voting : Route
