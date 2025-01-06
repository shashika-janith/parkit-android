package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.Provider
import com.google.gson.annotations.SerializedName

data class ProviderDto(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Float,
    val longitude: Float,
    val capacity: Int,
    @SerializedName("hourly_rate")
    val hourlyRate: Float,
    @SerializedName("available_spots")
    val availableSpots: Int,
    @SerializedName("contact_number")
    val contactNumber: String? = null,
    val email: String? = null
)

fun ProviderDto.toProvider(): Provider {
    return Provider(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        capacity = capacity,
        hourlyRate = hourlyRate,
        availableSpots = availableSpots,
        contactNumber = contactNumber,
        email = email
    )
}
