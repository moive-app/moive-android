package com.moive.app.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.core.designsystem.component.toast.ToastType
import com.moive.app.data.auth.repository.AuthRepository
import com.moive.app.data.user.repository.UserRepository
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.NavigateToLogin
import com.moive.app.presentation.mypage.MyPageContract.SideEffect.OnShowToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(MyPageContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<MyPageContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        getMyInfo()
    }

    private fun getMyInfo() = viewModelScope.launch {
        _uiState.update { it.copy(myInfoUiState = MyPageUiState.Loading) }

        userRepository.getMyInfo()
            .onSuccess { user ->
                _uiState.update {
                    it.copy(
                        myInfoUiState = MyPageUiState.Success,
                        name = user.nickname,
                        email = user.email,
                        profileImage = user.profileImageUrl,
                    )
                }
            }
            .onFailure { error ->
                Timber.tag(MY_PAGE_TAG).e(MY_INFO_FAILURE_MESSAGE)
                _uiState.update {
                    it.copy(
                        myInfoUiState = MyPageUiState.Failure(error.message ?: UNKNOWN_ERROR_MESSAGE)
                    )
                }
            }
    }

    fun dismissLogoutBottomSheet() {
        _uiState.update { it.copy(showLogoutBottomSheet = false) }
    }

    fun onLogoutClick() {
        _uiState.update { it.copy(showLogoutBottomSheet = true) }
    }

    fun onLogoutConfirmClick() {
        dismissLogoutBottomSheet()
        postLogout()
    }

    private fun postLogout() = viewModelScope.launch {
        authRepository.postLogout()
            .onSuccess {
                _sideEffect.send(NavigateToLogin)
            }
            .onFailure {
                _sideEffect.send(OnShowToast(LOG_OUT_FAILURE_MESSAGE, ToastType.ERROR))
            }
    }

    companion object {
        private const val MY_PAGE_TAG = "MyPage"
        private const val LOG_OUT_FAILURE_MESSAGE = "로그아웃하지 못했어요. 잠시 후 다시 시도해주세요."
        private const val MY_INFO_FAILURE_MESSAGE = "내 정보를 불러오지 못했어요. 잠시 후 다시 시도해주세요."
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
