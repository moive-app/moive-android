package com.moive.app.presentation.meeting.complete.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.complete.MeetingCompleteRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingComplete(
    meetingId: Long,
    navOptions: NavOptions? = null
) = navigate(MeetingComplete(meetingId), navOptions)

fun NavGraphBuilder.meetingCompleteGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MeetingComplete> {
        MeetingCompleteRoute(
            navigateBack = navController.safePopBackStack(),
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data class MeetingComplete(
    val meetingId: Long
) : Route
