package org.sopt.at.feature.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.local.AuthPreferences
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.response.MyNicknameResponse
import org.sopt.at.data.repository.MainRepository
import javax.inject.Inject

sealed class MyEvent {
    object OnLogoutClick : MyEvent()
    object onEditNicknameClick : MyEvent()
}

sealed class MyEffect {
    object NavigateToSignIn : MyEffect()
    object NavigateToEditNickname : MyEffect()
}

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MainRepository,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<BaseState<MyNicknameResponse>>(BaseState.Idle)
    val uiState: StateFlow<BaseState<MyNicknameResponse>> = _uiState

    private val _effect = Channel<MyEffect>(Channel.BUFFERED)
    val effect: Flow<MyEffect> = _effect.receiveAsFlow()

    private val _nickname = MutableStateFlow<String?>("")
    val nickname: StateFlow<String?> = _nickname

    fun sendEvent(event: MyEvent) {
        when (event) {
            MyEvent.OnLogoutClick -> logout()
            MyEvent.onEditNicknameClick -> onEditNicknameClick()
        }
    }

    init {
        getMyNickname()
    }

    private fun onEditNicknameClick() {
        viewModelScope.launch {
            _effect.send(MyEffect.NavigateToEditNickname)
        }
    }

    private fun logout() {
        viewModelScope.launch {
            authPreferences.logout()
            _effect.send(MyEffect.NavigateToSignIn)
        }
    }

    private fun getMyNickname() {
        viewModelScope.launch {
            authPreferences.userId.collect { token ->
                repository.getMyNickname(token).collect { result ->
                    when (result) {
                        is BaseState.Error -> {}
                        is BaseState.Success -> {
                            _nickname.value = result.data.data.nickname
                        }

                        BaseState.Idle -> _uiState.value = BaseState.Idle
                    }
                }
            }

        }
    }
}