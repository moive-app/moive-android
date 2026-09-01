package com.moive.app.presentation.meeting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.home.HomeRoute
import com.moive.app.presentation.meeting.MeetingRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMeeting(
    navOptions: NavOptions? = null
) = navigate(Meeting, navOptions)

fun NavGraphBuilder.meetingGraph(
    innerPadding: PaddingValues,
) {
    composable<Meeting> {
        MeetingRoute(
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Meeting: Route
