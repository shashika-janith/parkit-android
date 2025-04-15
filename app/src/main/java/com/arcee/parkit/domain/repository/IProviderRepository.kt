package com.arcee.parkit.domain.repository

import androidx.paging.PagingData
import com.arcee.parkit.data.remote.dto.ProviderResponseDto
import com.arcee.parkit.domain.model.Provider
import kotlinx.coroutines.flow.Flow

interface IProviderRepository {
    fun getPagedItems(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<Provider>>

    suspend fun getProviderById(id: Int): ProviderResponseDto
}