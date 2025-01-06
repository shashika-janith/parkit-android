package com.arcee.parkit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("providers")
data class ProviderEntity(
    @PrimaryKey
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
