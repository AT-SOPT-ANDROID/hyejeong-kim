package org.sopt.at.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignUpResponse
import org.sopt.at.data.repository.UserRepository
import javax.inject.Inject

sealed class SignUpIntent {
    data class PostSignUp(val request: SignUpRequest) : SignUpIntent()
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun processIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.PostSignUp -> postSignUp(intent.request)
        }
    }

    private val _uiState = MutableStateFlow<BaseState<SignUpResponse>>(BaseState.Idle)
    val uiState: StateFlow<BaseState<SignUpResponse>> = _uiState.asStateFlow()

    private fun postSignUp(request: SignUpRequest) {
        viewModelScope.launch {
            userRepository.postSignUp(request).collect { result ->
                _uiState.value = result
            }
        }
    }
}