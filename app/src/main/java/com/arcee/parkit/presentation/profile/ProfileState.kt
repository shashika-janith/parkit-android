package com.arcee.parkit.presentation.profile

import com.arcee.parkit.domain.model.User

data class ProfileState(
    val isLoading: Boolean = false,
    val error: String = "",
    val user: User? = null,
)
