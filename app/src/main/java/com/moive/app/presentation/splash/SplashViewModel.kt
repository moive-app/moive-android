package com.moive.app.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.core.utils.suspendRunCatching
import com.moive.app.data.auth.repository.AuthRepository
import com.moive.app.presentation.splash.SplashContract.SideEffect.NavigateToHome
import com.moive.app.presentation.splash.SplashContract.SideEffect.NavigateToLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _sideEffect = Channel<SplashContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun tryAutoLogin() {
        viewModelScope.launch {
            val delayTime = async {
                delay(SPLASH_DELAY.milliseconds)
            }

            val reissueToken = async {
                suspendRunCatching { postReissue() }
            }

            delayTime.await()
            reissueToken.await()
                .onSuccess {
                    Timber.tag(AUTHORIZATION).d(AUTO_LOGIN_SUCCESS_MESSAGE)
                    _sideEffect.send(NavigateToHome)
                }
                .onFailure { error ->
                    Timber.tag(AUTHORIZATION).e("$AUTO_LOGIN_FAILURE_MESSAGE $error")
                    _sideEffect.send(NavigateToLogin)
                }
        }
    }


    private suspend fun postReissue() {
        authRepository.postReissue()
            .onSuccess {
                Timber.tag(AUTHORIZATION).d(REISSUE_SUCCESS_MESSAGE)
            }
            .onFailure { error ->
                Timber.tag(AUTHORIZATION).e("$REISSUE_FAILURE_MESSAGE ${error.message}")
                throw error
            }
    }

    companion object {
        private const val SPLASH_DELAY = 2000L
        private const val AUTHORIZATION = "Authorization"
        private const val REISSUE_SUCCESS_MESSAGE = "토큰 재발급 성공"
        private const val REISSUE_FAILURE_MESSAGE = "토큰 재발급 실패"
        private const val AUTO_LOGIN_SUCCESS_MESSAGE = "자동 로그인 성공"
        private const val AUTO_LOGIN_FAILURE_MESSAGE = "자동 로그인 실패"
    }

}
