package com.moive.app.presentation.meeting.infoconfirm.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.meeting.create.navigation.MeetingCreation
import com.moive.app.presentation.meeting.detail.navigation.navigateToMeetingDetail
import com.moive.app.presentation.meeting.infoconfirm.MeetingInfoConfirmRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMeetingInfoConfirm(
    navOptions: NavOptions? = null
) = navigate(MeetingInfoConfirm, navOptions)

fun NavGraphBuilder.meetingInfoConfirmGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MeetingInfoConfirm> {
        MeetingInfoConfirmRoute(
            navigateToMeetingDetail = {
                navController.navigateToMeetingDetail(
                    navOptions = navOptions {
                        popUpTo<MeetingCreation> {
                            inclusive = true
                        }
                    },
                )
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object MeetingInfoConfirm : Route
