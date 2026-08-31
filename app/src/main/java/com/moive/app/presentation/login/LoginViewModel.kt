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
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var kakaoAccessToken: String? = null

    fun postKakaoLogin(token: String) = viewModelScope.launch {
        kakaoAccessToken = token

        authRepository.postKakaoLogin(token)
            .onSuccess {
                _uiState.update {
                    it.copy(isLoginComplete = true)
                }
            }
            .onFailure {
                // 실패) loginScreen -> 토스트 메세지
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
                    // Todo: 토스트 메세지
                }

            _uiState.update { it.copy(isSignUpSubmitting = false) }
        }
    }

    fun showToast(
        text: String,
    ) {
        // Todo: Toast 띄우기
    }
}
