package com.moive.app.presentation.splash.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.navigation.Route
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashGraph() {
    composable<Splash> {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.primary.default),
        )
    }
}

@Serializable
data object Splash : Route
