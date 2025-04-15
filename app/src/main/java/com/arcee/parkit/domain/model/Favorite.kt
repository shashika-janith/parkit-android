package com.arcee.parkit.domain.model

data class Favorite(
    val id: Long,
    val entityId: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int,
    val hourlyRate: Float,
    val phone: String?,
    val images: Array<String>
)
