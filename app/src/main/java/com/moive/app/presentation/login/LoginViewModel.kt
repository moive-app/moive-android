package com.moive.app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.auth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    fun postKakaoLogin(token: String) = viewModelScope.launch {
        authRepository.postKakaoLogin(token)
            .onSuccess {
                Timber.tag("KakaoLogin").i("카카오톡 로그인 성공 $token")
                // 성공) loginScreen -> 약관 동의 바텀시트 up
            }
            .onFailure {
                // 실패) loginScreen -> 토스트 메세지
            }
    }

    fun postSignUp() {
        //Todo: /auth/signup 회원가입 호출
        // 약관동의 항목 동의 여부, 액세스 토큰 반환
        // 성공) 우선 mypage로 이동
    }

    fun showToast(
        text: String,
    ) {
        // Todo: Toast 띄우기
    }
}
