package com.moive.app.presentation.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.moive.app.core.analytics.AnalyticsTracker
import com.moive.app.core.analytics.event.AnalyticsEvent
import com.moive.app.presentation.splash.navigation.Splash

@Composable
fun TrackScreenViews(
    navController: NavController,
    analyticsTracker: AnalyticsTracker,
) {
    var lastTrackedEntryId by rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            if (entry.id == lastTrackedEntryId) return@collect
            lastTrackedEntryId = entry.id

            val destination = entry.destination
            if (destination.hasRoute(Splash::class)) return@collect
            val screenName = destination.toScreenName() ?: return@collect

            analyticsTracker.track(
                AnalyticsEvent.ScreenViewed(
                    screenName = screenName,
                    meetingId = entry.arguments?.getMeetingId(),
                ),
            )
        }
    }
}

private fun NavDestination.toScreenName(): String? =
    route
        ?.substringBefore('/')
        ?.substringBefore('?')
        ?.substringAfterLast('.')

private fun Bundle.getMeetingId(): Long? =
    if (containsKey(KEY_MEETING_ID)) getLong(KEY_MEETING_ID) else null

private const val KEY_MEETING_ID = "meetingId"
