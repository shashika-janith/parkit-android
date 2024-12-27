package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.Notification
import com.google.gson.annotations.SerializedName

data class NotificationDto(
    @SerializedName("notification_id")
    val notificationId: Int,
    val title: String,
    val message: String,
    val timestamp: String,
    val type: String,
    @SerializedName("is_read")
    val isRead: Boolean
)

fun NotificationDto.toNotification(): Notification {
    return Notification(
        notificationId = notificationId,
        title = title,
        message = message,
        timestamp = timestamp,
        type = type,
        isRead = isRead
    )
}

