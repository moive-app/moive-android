package com.moive.app.presentation.meeting.confirmed.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.confirmed.MeetingConfirmedRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingConfirmed(
    navOptions: NavOptions? = null
) = navigate(MeetingConfirmed, navOptions)

fun NavGraphBuilder.meetingConfirmedGraph(
    innerPadding: PaddingValues,
) {
    composable<MeetingConfirmed> {
        MeetingConfirmedRoute(
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object MeetingConfirmed : Route
