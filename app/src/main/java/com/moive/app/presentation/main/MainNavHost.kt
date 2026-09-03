package com.moive.app.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.moive.app.presentation.home.navigation.homeGraph
import com.moive.app.presentation.login.navigation.loginGraph
import com.moive.app.presentation.login.signup.navigation.signUpCompleteGraph
import com.moive.app.presentation.meeting.complete.navigation.meetingCompleteGraph
import com.moive.app.presentation.meeting.confirmed.navigation.meetingConfirmedGraph
import com.moive.app.presentation.meeting.detail.navigation.meetingDetailGraph
import com.moive.app.presentation.meeting.create.navigation.meetingCreationGraph
import com.moive.app.presentation.meeting.infoconfirm.navigation.meetingInfoConfirmGraph
import com.moive.app.presentation.meeting.list.navigation.meetingListGraph
import com.moive.app.presentation.mypage.navigation.myPageGraph
import com.moive.app.presentation.splash.navigation.splashGraph

@Composable
fun MainNavHost(
    appState: MainAppState,
    innerPadding: PaddingValues,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = appState.startDestination,
    ) {
        splashGraph(
            navController = navController,
        )

        loginGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        signUpCompleteGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        homeGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        myPageGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        meetingCreationGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        meetingInfoConfirmGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        meetingListGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        meetingDetailGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        meetingConfirmedGraph(
            innerPadding = innerPadding,
        )

        meetingCompleteGraph(
            innerPadding = innerPadding,
        )

    }
}
