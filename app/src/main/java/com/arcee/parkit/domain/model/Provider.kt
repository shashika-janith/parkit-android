package com.arcee.parkit.domain.model

data class Provider(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Float,
    val longitude: Float,
    val capacity: Int,
    val hourlyRate: Float,
    val availableSpots: Int,
    val contactNumber: String? = null,
    val email: String? = null
)
