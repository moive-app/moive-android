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
import com.moive.app.presentation.login.LoginRoute
import com.moive.app.presentation.mypage.navigation.navigateToMyPage
import kotlinx.serialization.Serializable

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) = navigate(Login, navOptions)

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Login> {
        LoginRoute(
            navigateToMyPage = {
                navController.navigateToMyPage(
                    navOptions = navController.clearBackStackNavOptions()
                )
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Login: Route
