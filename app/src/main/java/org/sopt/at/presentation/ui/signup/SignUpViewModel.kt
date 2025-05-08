package org.sopt.at.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignUpResponse
import org.sopt.at.data.repository.AuthRepository
import javax.inject.Inject

sealed class SignUpEvent {
    data class PostSignUp(val request: SignUpRequest) : SignUpEvent()
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun sendEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.PostSignUp -> postSignUp(event.request)
        }
    }

    private val _uiState = MutableStateFlow<BaseState<SignUpResponse>>(BaseState.Idle)
    val uiState: StateFlow<BaseState<SignUpResponse>> = _uiState

    private fun postSignUp(request: SignUpRequest) {
        viewModelScope.launch {
            authRepository.postSignUp(request).collect { result ->
                _uiState.value = result
            }
        }
    }
}