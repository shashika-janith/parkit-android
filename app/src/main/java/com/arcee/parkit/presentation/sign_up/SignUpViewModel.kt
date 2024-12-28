package com.arcee.parkit.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.remote.dto.SignUpDto
import com.arcee.parkit.domain.use_case.sign_up.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun signUp(dto: SignUpDto) {
        signUpUseCase(dto).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    // TODO: Handle OTP verification.
                }

                is Resource.Error -> {
                    _state.value =
                        SignUpState(
                            error = result.message ?: "An unexpected error occurred.",
                            isLoading = false
                        )
                }

                is Resource.Loading -> {
                    _state.value = SignUpState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}