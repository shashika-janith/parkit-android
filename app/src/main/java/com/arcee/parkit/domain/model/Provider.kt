package com.arcee.parkit.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Provider(
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val capacity: Int,
    val hourlyRate: Float,
    val phone: String?,
    val images: Array<String>,
    val isFavorite: Boolean,
) : Parcelable
