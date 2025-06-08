package org.sopt.at.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.response.UsersResponse
import org.sopt.at.data.repository.MainRepository
import javax.inject.Inject

sealed class SearchEvent {
    data class OnQueryChanged(val query: String) : SearchEvent()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<BaseState<UsersResponse>>(BaseState.Idle)
    val uiState: StateFlow<BaseState<UsersResponse>> = _uiState

    private val _nicknameList = MutableStateFlow<List<String>>(emptyList())
    val nicknameList: StateFlow<List<String>> = _nicknameList

    private val queryFlow = MutableStateFlow("")

    fun sendEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChanged -> {
                queryFlow.value = event.query
            }
        }
    }

    init {
        search()
    }

    @OptIn(FlowPreview::class)
    private fun search() {
        viewModelScope.launch {
            queryFlow.debounce(300)
                .distinctUntilChanged()
                .collectLatest {
                    getUsers(it)
                }
        }
    }

    private fun getUsers(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            repository.getUsers(query).collect { result ->
                when (result) {
                    is BaseState.Error -> {

                    }

                    is BaseState.Success -> {
                        _uiState.value = result
                        _nicknameList.value = result.data.data.nicknameList
                    }

                    BaseState.Idle -> {
                        _uiState.value = BaseState.Idle
                    }
                }
            }
        }
    }
}