package com.arcee.parkit.data.remote.dto

data class NotificationListResponseDto(
    val status: String,
    val message: String,
    val data: List<NotificationDto>
)
