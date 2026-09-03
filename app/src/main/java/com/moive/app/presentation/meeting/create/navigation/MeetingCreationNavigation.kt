package com.moive.app.presentation.meeting.create.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.create.MeetingCreationRoute
import com.moive.app.presentation.meeting.infoconfirm.navigation.navigateToMeetingInfoConfirm
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingCreation(
    navOptions: NavOptions? = null
) = navigate(MeetingCreation, navOptions)

fun NavGraphBuilder.meetingCreationGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MeetingCreation> {
        MeetingCreationRoute(
            navigateToMeetingInfoConfirm = navController::navigateToMeetingInfoConfirm,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object MeetingCreation: Route
