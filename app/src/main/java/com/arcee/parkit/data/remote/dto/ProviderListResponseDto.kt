package com.arcee.parkit.data.remote.dto

data class ProviderListResponseDto(
    val status: String,
    val message: String,
    val data: List<ProviderDto>
)
