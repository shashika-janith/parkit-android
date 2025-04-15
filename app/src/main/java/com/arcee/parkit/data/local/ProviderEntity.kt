package com.arcee.parkit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arcee.parkit.common.SafetySecurityEnum

@Entity("providers")
data class ProviderEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val phone: String? = null,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val hourlyRate: Float,
    val security: List<SafetySecurityEnum>,
    val capacity: Int,
    val occupied: Int,
)

