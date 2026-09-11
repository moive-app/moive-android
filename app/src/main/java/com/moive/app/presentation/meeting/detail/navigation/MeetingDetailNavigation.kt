package com.moive.app.presentation.meeting.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.condition.navigation.navigateToCondition
import com.moive.app.presentation.meeting.complete.navigation.navigateToMeetingComplete
import com.moive.app.presentation.meeting.confirmed.navigation.navigateToMeetingConfirmed
import com.moive.app.presentation.meeting.detail.MeetingDetailRoute
import com.moive.app.presentation.voting.navigation.navigateToVoting
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingDetail(
    meetingId: Long,
    navOptions: NavOptions? = null,
) = navigate(MeetingDetail(meetingId), navOptions)

fun NavGraphBuilder.meetingDetailGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MeetingDetail> {
        MeetingDetailRoute(
            navigateBack = navController.safePopBackStack(),
            navigateToCondition = navController::navigateToCondition,
            navigateToVoting = navController::navigateToVoting,
            navigateToMeetingConfirmed = navController::navigateToMeetingConfirmed,
            navigateToMeetingComplete = navController::navigateToMeetingComplete,
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data class MeetingDetail(
    val meetingId: Long
) : Route
