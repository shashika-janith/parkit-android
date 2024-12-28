package com.arcee.parkit.presentation.notifications

import com.arcee.parkit.domain.model.Notification

data class NotificationsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val notifications: List<Notification> = emptyList(),
)
