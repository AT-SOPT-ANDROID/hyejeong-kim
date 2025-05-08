package org.sopt.at.presentation.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignInRequest
import org.sopt.at.data.model.response.SignInResponse
import org.sopt.at.data.repository.UserRepository
import javax.inject.Inject

sealed class SignInEvent {
    data class PostLogin(val request: SignInRequest) : SignInEvent()
}

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun sendEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.PostLogin -> postSignIn(event.request)
        }
    }

    private val _uiState = MutableStateFlow<BaseState<SignInResponse>>(BaseState.Idle)
    val uiState: StateFlow<BaseState<SignInResponse>> = _uiState

    private val _event = MutableSharedFlow<SignInEvent>()
    val event: SharedFlow<SignInEvent> = _event

    private fun postSignIn(request: SignInRequest) {
        viewModelScope.launch {
            userRepository.postSignIn(request).collect { result ->
                _uiState.value = result
            }
        }
    }
}