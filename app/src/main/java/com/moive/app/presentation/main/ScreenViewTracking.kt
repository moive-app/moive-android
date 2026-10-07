package com.moive.app.presentation.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.moive.app.core.analytics.AnalyticsTracker
import com.moive.app.core.analytics.event.AnalyticsEvent
import com.moive.app.presentation.splash.navigation.Splash
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun TrackScreenViews(
    navController: NavController,
    analyticsTracker: AnalyticsTracker,
    viewModel: ScreenViewTrackingViewModel = hiltViewModel(),
) {
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            if (entry.id == viewModel.lastTrackedEntryId) return@collect
            viewModel.lastTrackedEntryId = entry.id

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

// 구성 변경 시 중복 기록은 막고, 프로세스 복원 후에는 현재 화면을 다시 기록하도록 Activity 범위에 보관
@HiltViewModel
class ScreenViewTrackingViewModel @Inject constructor() : ViewModel() {
    var lastTrackedEntryId: String? = null
}

private fun NavDestination.toScreenName(): String? =
    route
        ?.substringBefore('/')
        ?.substringBefore('?')
        ?.substringAfterLast('.')

private fun Bundle.getMeetingId(): Long? =
    if (containsKey(KEY_MEETING_ID)) getLong(KEY_MEETING_ID) else null

private const val KEY_MEETING_ID = "meetingId"
