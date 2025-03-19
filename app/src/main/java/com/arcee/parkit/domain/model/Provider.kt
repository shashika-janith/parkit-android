package com.arcee.parkit.domain.model

data class Provider(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int,
    val hourlyRate: Float,
    val phone: String?,
)
