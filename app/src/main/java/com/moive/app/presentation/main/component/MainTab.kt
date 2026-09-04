package com.moive.app.presentation.main.component

import androidx.annotation.DrawableRes
import com.moive.app.R
import com.moive.app.core.navigation.MainTabRoute
import com.moive.app.core.navigation.Route
import com.moive.app.presentation.home.navigation.Home
import com.moive.app.presentation.mypage.navigation.MyPage

enum class MainTab(
    @DrawableRes val iconRes: Int,
    val titleRes: String,
    val route: MainTabRoute,
) {
    HOME(
        iconRes = R.drawable.ic_home_28,
        titleRes = "home",
        route = Home,
    ),

    MYPAGE(
        iconRes = R.drawable.ic_profile_28,
        titleRes = "mypage",
        route = MyPage,
    );

    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        fun contains(predicate: (Route) -> Boolean): Boolean {
            return entries.any { predicate(it.route) }
        }
    }
}
