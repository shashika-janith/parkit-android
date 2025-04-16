package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.common.SafetySecurityEnum
import com.arcee.parkit.domain.model.Provider

data class ProviderDto(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int,
    val occupied: Int,
    val rate: Float,
    val phone: String? = null,
    val email: String? = null,
    val security: List<SafetySecurityEnum>,
    val type: String
)

fun ProviderDto.toProvider(): Provider {
    return Provider(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        capacity = capacity,
        hourlyRate = rate,
        phone = phone,
        images = arrayOf(),
        isFavorite = false
    )
}
