package org.sopt.at.presentation.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignInRequest
import org.sopt.at.data.model.response.SignInResponse
import org.sopt.at.data.repository.UserRepository
import javax.inject.Inject

sealed class SignInEvent {
    data class PostLogin(val request: SignInRequest) : SignInEvent()
}

sealed class SignInEffect {
    object NavigateToHome : SignInEffect()
    data class ShowSnackbar(val message: String) : SignInEffect()
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

    private val _effect = Channel<SignInEffect>(Channel.BUFFERED)
    val effect: Flow<SignInEffect> = _effect.receiveAsFlow()

    private fun postSignIn(request: SignInRequest) {
        viewModelScope.launch {
            userRepository.postSignIn(request).collect { result ->
                when (result) {
                    is BaseState.Error -> {
                        _effect.send(SignInEffect.ShowSnackbar(result.message))
                    }

                    is BaseState.Success -> {
                        _uiState.value = result
                        _event.emit(SignInEvent.PostLogin(request))
                        _effect.send(SignInEffect.NavigateToHome)
                    }

                    BaseState.Idle -> {
                        _uiState.value = BaseState.Idle
                    }
                }
            }
        }
    }
}