package com.arcee.parkit.data.repository

import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.remote.dto.ProviderListResponseDto
import com.arcee.parkit.domain.repository.IProviderRepository
import javax.inject.Inject

class ProviderRepositoryImpl @Inject constructor(private val api: IParkItApi) :
    IProviderRepository {
    override suspend fun getProviders(
        pageNo: Int,
        pageSize: Int
    ): ProviderListResponseDto {
        return api.getProviders(pageNo, pageSize)
    }
}