package com.arcee.parkit.data.remote.dto

data class FilterParkingAreasDto(
    val pageNo: Int,
    val pageSize: Int,
    val latitude: Double,
    val longitude: Double
)
