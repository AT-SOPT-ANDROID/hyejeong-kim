package org.sopt.at.feature.editnickname

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
import org.sopt.at.data.model.request.EditNicknameRequest
import org.sopt.at.data.model.response.EditNicknameResponse
import org.sopt.at.data.model.response.SignInResponse
import org.sopt.at.data.repository.MainRepository
import org.sopt.at.feature.signin.SignInEffect
import javax.inject.Inject

sealed class EditNicknameEvent {
    data class PatchNickname(val request: EditNicknameRequest) : EditNicknameEvent()
}

sealed class EditNicknameEffect {
    object NavigateToMy : EditNicknameEffect()
    data class ShowSnackbar(val message: String) : EditNicknameEffect()
}

@HiltViewModel
class EditNicknameViewModel @Inject constructor(
    private val repository: MainRepository,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _effect = Channel<EditNicknameEffect>(Channel.BUFFERED)
    val effect: Flow<EditNicknameEffect> = _effect.receiveAsFlow()

    private val _uiState = MutableStateFlow<BaseState<EditNicknameResponse>>(BaseState.Idle)
    val uiState: StateFlow<BaseState<EditNicknameResponse>> = _uiState

    fun sendEvent(event: EditNicknameEvent) {
        when (event) {
            is EditNicknameEvent.PatchNickname -> patchNickname(event.request)
        }
    }

    private fun patchNickname(request: EditNicknameRequest) {
        viewModelScope.launch {
            authPreferences.userId.collect {
                repository.patchNickname(token = it, request = request).collect { result ->
                    when (result) {
                        is BaseState.Error -> {
                            _effect.send(EditNicknameEffect.ShowSnackbar(result.message))
                        }

                        is BaseState.Success -> {
                            _effect.send(EditNicknameEffect.NavigateToMy)
                        }
                        BaseState.Idle -> {
                            _uiState.value = BaseState.Idle
                        }
                    }
                }
            }

        }
    }

}