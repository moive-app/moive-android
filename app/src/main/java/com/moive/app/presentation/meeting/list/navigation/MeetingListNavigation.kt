package com.moive.app.presentation.meeting.list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.complete.navigation.navigateToMeetingComplete
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
            navigateToMeetingDetail = navController::navigateToMeetingDetail,
            navigateToMeetingComplete = navController::navigateToMeetingComplete,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object MeetingList : Route
