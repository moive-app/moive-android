package com.moive.app.presentation.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.clearBackStackNavOptions
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.home.navigation.navigateToHome
import com.moive.app.presentation.login.LoginRoute
import com.moive.app.presentation.login.signup.navigation.navigateToSignUpComplete
import kotlinx.serialization.Serializable

fun NavController.navigateToLogin(
    navOptions: NavOptions? = clearBackStackNavOptions()
) = navigate(Login, navOptions)

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Login> {
        LoginRoute(
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navController.clearBackStackNavOptions()
                )
            },
            navigateToSignUpComplete = navController::navigateToSignUpComplete,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Login: Route
