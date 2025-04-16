package com.arcee.parkit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favourites")
data class FavouriteEntity(
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