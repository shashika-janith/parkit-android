package com.arcee.parkit.domain.repository

import com.arcee.parkit.data.remote.dto.ProviderListResponseDto

interface IProviderRepository {
    suspend fun getProviders(pageNo: Int, pageSize: Int): ProviderListResponseDto
}