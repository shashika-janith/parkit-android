package com.arcee.parkit.presentation.sign_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.remote.dto.SignInDto
import com.arcee.parkit.data.repository.UserPreferencesRepository
import com.arcee.parkit.domain.model.User
import com.arcee.parkit.domain.use_case.sign_in.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun signIn(dto: SignInDto) {
        signInUseCase(dto).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val user: User? = result.data
                    _state.value = SignInState(userSession = user)

                    if (user != null) {
                        preferencesRepository.saveCurrentUser(user)
                    }
                }

                is Resource.Error -> {
                    _state.value =
                        SignInState(
                            error = result.message ?: "An unexpected error occurred.",
                            isLoading = false
                        )
                }

                is Resource.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}