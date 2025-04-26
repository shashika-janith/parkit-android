package com.arcee.parkit.domain.repository

import androidx.paging.PagingData
import com.arcee.parkit.data.remote.dto.AddToFavoritesResponseDto
import com.arcee.parkit.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    suspend fun addToFavorites(id: Long): AddToFavoritesResponseDto

    suspend fun removeFromFavorites(id: Long): AddToFavoritesResponseDto

    fun getPagedItems(): Flow<PagingData<Favorite>>
}