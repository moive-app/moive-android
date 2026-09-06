package com.moive.app.presentation.withdraw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moive.app.presentation.withdraw.WithDrawContract.SideEffect
import com.moive.app.presentation.withdraw.WithDrawContract.SideEffect.NavigateToLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithDrawViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(WithDrawContract.State())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

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

    fun postWithDraw() = viewModelScope.launch {
        _sideEffect.send(NavigateToLogin)
    }

}
