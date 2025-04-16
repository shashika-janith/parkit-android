package com.arcee.parkit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.remote.PagingSourceFactory
import com.arcee.parkit.data.remote.dto.ProviderResponseDto
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.domain.repository.IProviderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProviderRepositoryImpl @Inject constructor(
    private val api: IParkItApi,
    private val pagingSourceFactory: PagingSourceFactory
) :
    IProviderRepository {
    override fun getPagedItems(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<Provider>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                pagingSourceFactory.createProviderRemotePagingSource(
                    latitude,
                    longitude
                )
            }).flow
    }

    override suspend fun getProviderById(id: Int): ProviderResponseDto {
        return api.getProviderById(id)
    }
}