package com.moive.app.presentation.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.clearBackStackNavOptions
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.home.navigation.navigateToHome
import com.moive.app.presentation.login.navigation.navigateToLogin
import com.moive.app.presentation.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashGraph(
    navController: NavController,
) {
    composable<Splash> {
        SplashRoute(
            navigateToLogin = {
                navController.navigateToLogin(
                    navOptions = navController.clearBackStackNavOptions()
                )
            },
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navController.clearBackStackNavOptions()
                )
            },
        )
    }
}

@Serializable
data object Splash : Route
