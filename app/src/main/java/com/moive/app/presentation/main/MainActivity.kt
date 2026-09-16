package com.moive.app.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.network.token.AuthManager
import com.moive.app.data.meeting.repository.MeetingRepository
import com.moive.app.presentation.login.navigation.navigateToLogin
import com.moive.app.presentation.meeting.detail.navigation.navigateToMeetingDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authManager: AuthManager

    @Inject
    lateinit var meetingRepository: MeetingRepository

    private val pendingInviteCode = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        pendingInviteCode.value = intent.extractInviteCode()
        setIntent(Intent())
        setContent {
            MoiveTheme {
                val appState = rememberMainAppState()

                LaunchedEffect(Unit) {
                    authManager.authEvent.collect {
                        appState.navController.navigateToLogin()
                    }
                }

                LaunchedEffect(pendingInviteCode.value) {
                    val inviteCode = pendingInviteCode.value ?: return@LaunchedEffect

                    appState.isSignedIn.first { it }

                    meetingRepository.postMeetingJoin(inviteCode)
                        .onSuccess { join ->
                            appState.navController.navigateToMeetingDetail(join.meetingId)
                        }
                        .onFailure { error ->
                            Timber.tag(TAG).e(error)
                        }

                    pendingInviteCode.value = null
                }

                MainScreen(
                    appState = appState,
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        pendingInviteCode.value = intent.extractInviteCode()
        setIntent(Intent())
    }

    companion object {
        private const val TAG = "Invite"
    }
}

private fun Intent.extractInviteCode(): String? =
    takeIf { it.action == Intent.ACTION_VIEW }?.data?.lastPathSegment
