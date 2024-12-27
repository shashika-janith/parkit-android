package com.arcee.parkit.domain.model

data class Notification(
    val notificationId: Int,
    val title: String,
    val message: String,
    val timestamp: String,
    val type: String,
    val isRead: Boolean
)