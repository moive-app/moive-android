package com.moive.app.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.navigation.MainTabRoute
import com.moive.app.presentation.home.HomeRoute
import com.moive.app.presentation.meeting.complete.navigation.navigateToMeetingComplete
import com.moive.app.presentation.meeting.detail.navigation.navigateToMeetingDetail
import com.moive.app.presentation.meeting.list.navigation.navigateToMeetingList
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) = navigate(Home, navOptions)

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Home> {
        HomeRoute(
            navigateToMeetingList = navController::navigateToMeetingList,
            navigateToMeetingDetail = navController::navigateToMeetingDetail,
            navigateToMeetingComplete = navController::navigateToMeetingComplete,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Home: MainTabRoute
