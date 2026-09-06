package com.moive.app.presentation.withdraw.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moive.app.core.extensions.safePopBackStack
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.login.navigation.navigateToLogin
import com.moive.app.presentation.withdraw.WithDrawRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToWithDraw(
    navOptions: NavOptions? = null
) = navigate(WithDraw, navOptions)

fun NavGraphBuilder.withDrawGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<WithDraw> {
        WithDrawRoute(
            navigateBack = navController.safePopBackStack(),
            navigateToLogin = navController::navigateToLogin,
            innerPadding = innerPadding,
        )
    }
}

@Serializable
data object WithDraw : Route
