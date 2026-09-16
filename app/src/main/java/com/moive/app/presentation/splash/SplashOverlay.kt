package com.moive.app.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.presentation.splash.SplashContract.SideEffect.NavigateToHome

@Composable
fun SplashOverlay(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isAnimationEnded by remember { mutableStateOf(false) }

    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash_animation)
    )
    val lottieState by animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = uiState.isSplashReady,
        restartOnPlay = true,
        speed = 1f,
        iterations = 1,
    )

    LaunchedEffect(lifeCycleOwner) {
        lifeCycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is NavigateToHome -> {
                        if (sideEffect.isAutoLoginSuccess) navigateToHome() else navigateToLogin()
                        viewModel.onReady()
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.tryAutoLogin()
    }

    LaunchedEffect(uiState.isSplashReady, lottieState) {
        if (uiState.isSplashReady && lottieState == 1f) {
            isAnimationEnded = true
        }
    }

    when {
        uiState.isSplashReady && isAnimationEnded -> Unit
        uiState.isSplashReady -> {
            LottieAnimation(
                composition = lottieComposition,
                progress = { lottieState },
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        else -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.primary.default),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashAnimationPreview() {
    MoiveTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.primary.default),
        ) {
            val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
            val lottieState by animateLottieCompositionAsState(
                composition = lottieComposition,
                restartOnPlay = true,
                speed = 1f,
                iterations = 1,
            )

            LottieAnimation(
                composition = lottieComposition,
                progress = { lottieState },
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
