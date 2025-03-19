package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.Provider
import com.google.gson.annotations.SerializedName

data class ProviderDto(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("availableSlots")
    val capacity: Int,
    val rate: Float,
    val phone: String? = null,
    val email: String? = null,
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
        phone = phone
    )
}
