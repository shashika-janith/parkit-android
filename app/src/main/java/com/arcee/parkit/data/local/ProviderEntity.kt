package com.arcee.parkit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("providers")
data class ProviderEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int,
    val hourlyRate: Float,
    val phone: String? = null,
)
