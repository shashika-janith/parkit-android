package com.arcee.parkit.data.remote.dto

data class AddToFavoritesResponseDto(
    val status: Boolean,
    val message: String,
    val data: ProviderDto
)
