package com.moive.app.presentation.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.moive.app.core.navigation.MainTabRoute
import com.moive.app.presentation.login.navigation.navigateToLogin
import com.moive.app.presentation.mypage.MyPageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMyPage(
    navOptions: NavOptions? = null
) = navigate(MyPage, navOptions)

fun NavGraphBuilder.myPageGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MyPage> {
        MyPageRoute(
            navigateToLogin = {
                navController.navigateToLogin(
                    navOptions {
                        popUpTo<MyPage> { inclusive = true }
                    }
                )
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object MyPage: MainTabRoute
