package com.moive.app.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.moive.app.presentation.home.navigation.homeGraph
import com.moive.app.presentation.login.navigation.loginGraph
import com.moive.app.presentation.mypage.navigation.myPageGraph

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

        loginGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

        homeGraph(
            innerPadding = innerPadding,
        )

        myPageGraph(
            navController = navController,
            innerPadding = innerPadding,
        )

    }
}
