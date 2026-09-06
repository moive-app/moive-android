package com.moive.app.presentation.meeting.create.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.create.MeetingCreationRoute
import com.moive.app.presentation.meeting.detail.navigation.navigateToMeetingDetail
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
            navigateBack = navController.safePopBackStack(),
            navigateToMeetingDetail = {
                navController.navigateToMeetingDetail(
                    navOptions = navOptions {
                        popUpTo<MeetingCreation> {
                            inclusive = true
                        }
                    },
                )
            },
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data object MeetingCreation: Route
