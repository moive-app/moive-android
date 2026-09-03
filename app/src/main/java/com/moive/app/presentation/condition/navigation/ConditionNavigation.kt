package com.moive.app.presentation.condition.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.condition.ConditionRoute
import com.moive.app.presentation.meeting.detail.navigation.MeetingDetail
import kotlinx.serialization.Serializable

fun NavController.navigateToCondition(
    navOptions: NavOptions? = null
) = navigate(Condition, navOptions)

fun NavGraphBuilder.conditionGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Condition> {
        ConditionRoute(
            navigateToMeetingDetail = {
                navController.popBackStack<MeetingDetail>(inclusive = false)
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Condition : Route
