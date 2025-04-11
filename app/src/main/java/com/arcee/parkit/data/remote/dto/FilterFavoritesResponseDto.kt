package com.arcee.parkit.data.remote.dto

data class FilterFavoritesResponseDto(
    val currentPage: Int,
    val pageSize: Int,
    val totalPages: Int,
    val total: Int,
    val data: List<FavoriteDto>
)
