package com.moive.app.presentation.condition.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.condition.ConditionRoute
import com.moive.app.presentation.meeting.detail.navigation.MeetingDetail
import kotlinx.serialization.Serializable

fun NavController.navigateToCondition(
    meetingId: Long,
    hasSchedule: Boolean = false,
    scheduledDate: String? = null,
    scheduledTime: String? = null,
    navOptions: NavOptions? = null,
) = navigate(Condition(meetingId, hasSchedule, scheduledDate, scheduledTime), navOptions)

fun NavGraphBuilder.conditionGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Condition> {
        ConditionRoute(
            navigateBack = navController.safePopBackStack(),
            navigateToMeetingDetail = {
                navController.popBackStack<MeetingDetail>(inclusive = false)
            },
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data class Condition(
    val meetingId: Long,
    val hasSchedule: Boolean = false,
    val scheduledDate: String? = null,
    val scheduledTime: String? = null,
) : Route
