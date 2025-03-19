package com.arcee.parkit.data.mappers

import com.arcee.parkit.data.local.ProviderEntity
import com.arcee.parkit.data.remote.dto.ProviderDto
import com.arcee.parkit.domain.model.Provider

fun ProviderDto.toProviderEntity(): ProviderEntity {
    return ProviderEntity(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        capacity = capacity,
        hourlyRate = rate,
        phone = phone
    )
}

fun ProviderEntity.toProvider(): Provider {
    return Provider(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        capacity = capacity,
        hourlyRate = hourlyRate,
        phone = phone,
    )
}