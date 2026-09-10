package com.moive.app.presentation.withdraw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.data.user.repository.UserRepository
import com.moive.app.presentation.withdraw.WithDrawContract.SideEffect
import com.moive.app.presentation.withdraw.WithDrawContract.SideEffect.NavigateToLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WithDrawViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WithDrawContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var job: Job? = null

    fun toggleAgreement() {
        _uiState.update { it.copy(isAgreed = !it.isAgreed) }
    }

    fun onWithdrawClick() {
        _uiState.update { it.copy(showConfirmBottomSheet = true) }
    }

    fun dismissWithDrawBottomSheet() {
        _uiState.update { it.copy(showConfirmBottomSheet = false) }
    }

    fun onWithDrawConfirmClick() {
        dismissWithDrawBottomSheet()
        postWithDraw()
    }

    fun postWithDraw() {
        if (job?.isActive == true) return
        viewModelScope.launch {
            _uiState.update { it.copy(withDrawUiState = WithDrawUiState.Loading) }

            userRepository.deleteWithdraw()
                .onSuccess {
                    _uiState.update { it.copy(withDrawUiState = WithDrawUiState.Success) }
                    _sideEffect.send(NavigateToLogin)
                }
                .onFailure { error ->
                    Timber.tag(WITH_DRAW_TAG).e(error)
                    _uiState.update {
                        it.copy(
                            withDrawUiState = WithDrawUiState.Failure(
                                error.message ?: UNKNOWN_ERROR_MESSAGE
                            )
                        )
                    }
                }
        }
    }

    companion object {
        private const val WITH_DRAW_TAG = "WithDraw"
        private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
