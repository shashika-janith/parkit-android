package com.arcee.parkit.data.remote.dto

data class ProviderListResponseDto(
    val currentPage: Int,
    val pageSize: Int,
    val totalPages: Int,
    val total: Int,
    val data: List<ProviderDto>
)
