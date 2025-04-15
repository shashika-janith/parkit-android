package com.arcee.parkit.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcee.parkit.data.repository.UserPreferencesRepository
import com.arcee.parkit.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        viewModelScope.launch {
            preferencesRepository.userPreferencesFlow.collect{ userPref ->
                if (userPref != null) {
                    _state.value = ProfileState(user = User(
                        id = -1,
                        name = userPref.name,
                        email = userPref.email,
                        phone = userPref.phone,
                        emailVerified = userPref.emailVerified,
                        phoneVerified = userPref.emailVerified,
                        roles = null,
                        accessToken = null
                    ))
                }
            }
        }
    }
}