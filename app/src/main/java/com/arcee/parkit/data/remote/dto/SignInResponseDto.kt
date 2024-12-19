package com.arcee.parkit.data.remote.dto

data class SignInResponseDto(
    val status: String,
    val message: String,
    val data: UserSessionDto
)