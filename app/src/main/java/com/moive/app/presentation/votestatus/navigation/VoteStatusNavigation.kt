package com.moive.app.presentation.votestatus.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.votestatus.VoteStatusRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToVoteStatus(
    navOptions: NavOptions? = null
) = navigate(VoteStatus, navOptions)

fun NavGraphBuilder.voteStatusGraph(
    innerPadding: PaddingValues,
) {
    composable<VoteStatus> {
        VoteStatusRoute(
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object VoteStatus : Route
