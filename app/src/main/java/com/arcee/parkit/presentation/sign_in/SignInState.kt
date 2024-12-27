package com.arcee.parkit.presentation.sign_in

import com.arcee.parkit.domain.model.User

data class SignInState(
    val isLoading: Boolean = false,
    val error: String = "",
    val userSession: User? = null,
)
