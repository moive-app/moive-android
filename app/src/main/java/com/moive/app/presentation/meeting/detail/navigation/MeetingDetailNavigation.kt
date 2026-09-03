package com.moive.app.presentation.meeting.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.condition.navigation.navigateToCondition
import com.moive.app.presentation.meeting.confirmed.navigation.navigateToMeetingConfirmed
import com.moive.app.presentation.meeting.detail.MeetingDetailRoute
import com.moive.app.presentation.recommendation.navigation.navigateToRecommendation
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingDetail(
    navOptions: NavOptions? = null
) = navigate(MeetingDetail, navOptions)

fun NavGraphBuilder.meetingDetailGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MeetingDetail> {
        MeetingDetailRoute(
            navigateToCondition = navController::navigateToCondition,
            navigateToRecommendation = navController::navigateToRecommendation,
            navigateToMeetingConfirmed = navController::navigateToMeetingConfirmed,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object MeetingDetail : Route
