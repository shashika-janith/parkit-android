package com.arcee.parkit.data.remote.dto

data class ProviderResponseDto(
    val status: Boolean,
    val message: String,
    val data: ProviderDto
)
