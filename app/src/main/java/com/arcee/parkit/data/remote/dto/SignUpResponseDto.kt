package com.arcee.parkit.data.remote.dto

data class SignUpResponseDto(
    val status: String,
    val message: String,
    val data: UserDto
)