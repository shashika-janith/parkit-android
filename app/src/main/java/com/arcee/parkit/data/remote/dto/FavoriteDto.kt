package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.common.SafetySecurityEnum
import com.arcee.parkit.domain.model.Favorite

class FavoriteDto(
    val id: Long,
    val entityId: Long,
    val phone: String? = null,
    val address: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int,
    val occupiedSlots: Int,
    val hourlyRate: Float,
    val email: String? = null,
    val type: String,
    val security: Array<SafetySecurityEnum>,
)

fun FavoriteDto.toFavorite(): Favorite {
    return Favorite(
        id = id,
        entityId = entityId,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        capacity = capacity,
        hourlyRate = hourlyRate,
        phone = phone,
        images = arrayOf()
    )
}

