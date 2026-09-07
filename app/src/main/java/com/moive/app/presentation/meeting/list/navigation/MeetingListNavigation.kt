package com.moive.app.presentation.meeting.list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.detail.navigation.navigateToMeetingDetail
import com.moive.app.presentation.meeting.list.MeetingListRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingList(
    navOptions: NavOptions? = null
) = navigate(MeetingList, navOptions)

fun NavGraphBuilder.meetingListGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MeetingList> {
        MeetingListRoute(
            navigateBack = navController.safePopBackStack(),
            navigateToMeetingDetail = navController::navigateToMeetingDetail,
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data object MeetingList : Route
