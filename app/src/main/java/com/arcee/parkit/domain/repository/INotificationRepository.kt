package com.arcee.parkit.domain.repository

import com.arcee.parkit.data.remote.dto.NotificationListResponseDto

interface INotificationRepository {
    suspend fun getNotifications(pageNo: Int, pageSize: Int): NotificationListResponseDto
}