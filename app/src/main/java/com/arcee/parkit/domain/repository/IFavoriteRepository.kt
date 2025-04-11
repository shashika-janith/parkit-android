package com.arcee.parkit.domain.repository

import androidx.paging.PagingData
import com.arcee.parkit.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    fun getPagedItems(): Flow<PagingData<Favorite>>
}