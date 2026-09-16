package com.moive.app.presentation.votestatus.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.votestatus.VoteStatusRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToVoteStatus(
    meetingId: Long,
    navOptions: NavOptions? = null
) = navigate(VoteStatus(meetingId), navOptions)

fun NavGraphBuilder.voteStatusGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<VoteStatus> {
        VoteStatusRoute(
            navigateBack = navController.safePopBackStack(),
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data class VoteStatus(
    val meetingId: Long
) : Route
