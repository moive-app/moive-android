package com.moive.app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.auth.repository.AuthRepository
import com.moive.app.presentation.login.LoginContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var kakaoAccessToken: String? = null

    fun postKakaoLogin(token: String) = viewModelScope.launch {
        kakaoAccessToken = token

        authRepository.postKakaoLogin(token)
            .onSuccess { result ->
                if (result.registered) {
                    kakaoAccessToken = null
                    _sideEffect.send(SideEffect.NavigateToMyPage)
                } else {
                    _uiState.update { it.copy(needRegister = true) }
                }
            }
            .onFailure {
                showToast(LOGIN_FAILURE_MESSAGE)
            }
    }

    fun onServiceCheck(agreed: Boolean) {
        _uiState.update { it.copy(isServiceAgreed = agreed) }
    }

    fun onPrivacyCheck(agreed: Boolean) {
        _uiState.update { it.copy(isPrivacyAgreed = agreed) }
    }

    fun onMarketingCheck(agreed: Boolean) {
        _uiState.update { it.copy(isMarketingAgreed = agreed) }
    }

    fun postSignUp() {
        if (_uiState.value.isSignUpSubmitting) return
        val token = kakaoAccessToken ?: return

        _uiState.update { it.copy(isSignUpSubmitting = true) }

        viewModelScope.launch {
            val currentState = _uiState.value

            authRepository.postSignUp(
                accessToken = token,
                isServiceAgreed = currentState.isServiceAgreed,
                isPrivacyAgreed = currentState.isPrivacyAgreed,
                isMarketingAgreed = currentState.isMarketingAgreed,
            )
                .onSuccess {
                    kakaoAccessToken = null
                    _sideEffect.send(SideEffect.NavigateToMyPage)
                }
                .onFailure {
                    showToast(SIGN_UP_FAILURE_MESSAGE)
                }

            _uiState.update { it.copy(isSignUpSubmitting = false) }
        }
    }

    fun showToast(
        text: String,
    ) = viewModelScope.launch {
        _sideEffect.send(SideEffect.OnShowToast(text))
    }

    companion object {
        private const val LOGIN_FAILURE_MESSAGE = "일시적인 오류가 발생했어요. 다시 시도해주세요."
        private const val SIGN_UP_FAILURE_MESSAGE = "로그인을 완료하지 못했어요. 다시 시도해주세요."
    }
}
