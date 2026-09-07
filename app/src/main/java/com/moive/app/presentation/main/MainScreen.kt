package com.moive.app.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.component.toast.LocalToastTrigger
import com.moive.app.core.designsystem.component.toast.MoiveToast
import com.moive.app.core.designsystem.component.toast.MoiveToastVisuals
import com.moive.app.core.designsystem.component.toast.ToastType
import com.moive.app.presentation.main.component.MainBottomBar
import com.moive.app.presentation.main.component.MainTab
import com.moive.app.presentation.meeting.create.navigation.navigateToMeetingCreation
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TOAST_DURATION = 3000L

@Composable
fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()
    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var job by remember { mutableStateOf<Job?>(null) }
    var bottomBarHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val onShowToast: (String, ToastType) -> Unit = { message, type ->
        job?.cancel()
        job = coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            launch {
                delay(TOAST_DURATION)
                snackbarHostState.currentSnackbarData?.dismiss()
            }

            snackbarHostState.showSnackbar(
                MoiveToastVisuals(
                    message = message,
                    type = type,
                    withDismissAction = false,
                ),
            )
        }
    }

    CompositionLocalProvider(
        LocalToastTrigger provides onShowToast,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                bottomBar = {
                    MainBottomBar(
                        isVisible = isBottomBarVisible,
                        tabs = MainTab.entries.toPersistentList(),
                        currentTab = currentTab,
                        onMeetingBtnClick = appState.navController::navigateToMeetingCreation,
                        onTabSelected = appState::navigate,
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                if (isBottomBarVisible) {
                                    bottomBarHeight = with(density) {
                                        coordinates.size.height.toDp()
                                    }
                                }
                            },
                    )
                },
            ) { innerPadding ->
                val contentInnerPadding = PaddingValues(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                    bottom = if (isBottomBarVisible) bottomBarHeight else 0.dp,
                )

                MainNavHost(
                    appState = appState,
                    innerPadding = contentInnerPadding,
                )
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = bottomBarHeight + 12.dp
                    )
                    .navigationBarsPadding(),
            ) { data ->

                val moiveToastVisuals = data.visuals as MoiveToastVisuals

                MoiveToast(
                    text = moiveToastVisuals.message,
                    type = moiveToastVisuals.type,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 80.dp),
                )
            }
        }
    }
}
