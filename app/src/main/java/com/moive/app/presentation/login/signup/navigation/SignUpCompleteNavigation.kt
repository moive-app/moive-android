package com.moive.app.presentation.login.signup.navigation

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
import com.moive.app.presentation.login.signup.SignUpCompleteRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignUpComplete(
    navOptions: NavOptions? = clearBackStackNavOptions()
) = navigate(SignUpComplete, navOptions)

fun NavGraphBuilder.signUpCompleteGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<SignUpComplete> {
        SignUpCompleteRoute(
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navController.clearBackStackNavOptions()
                )
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object SignUpComplete : Route
