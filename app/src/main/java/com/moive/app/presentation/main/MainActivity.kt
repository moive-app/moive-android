package com.moive.app.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.navOptions
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.network.token.AuthManager
import com.moive.app.presentation.login.navigation.navigateToLogin
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoiveTheme {
                val appState = rememberMainAppState()

                LaunchedEffect(Unit) {
                    authManager.authEvent.collect {
                        appState.navController.navigateToLogin()
                    }
                }

                MainScreen(
                    appState = appState,
                )
            }
        }
    }
}
