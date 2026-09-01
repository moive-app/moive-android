package com.moive.app.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.auth.repository.AuthRepository
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.NavigateToLogin
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.OnShowToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _sideEffect = Channel<MyPageContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun postLogout() = viewModelScope.launch {
        authRepository.postLogout()
            .onSuccess {
                _sideEffect.send(NavigateToLogin)
            }
            .onFailure {
                _sideEffect.send(OnShowToast(LOG_OUT_FAILURE_MESSAGE))
            }
    }

    companion object {
        private const val LOG_OUT_FAILURE_MESSAGE = "로그아웃하지 못했어요. 잠시 후 다시 시도해주세요."
    }
}
