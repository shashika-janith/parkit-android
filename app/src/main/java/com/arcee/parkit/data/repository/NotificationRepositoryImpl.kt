package com.arcee.parkit.data.repository

import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.remote.dto.NotificationListResponseDto
import com.arcee.parkit.domain.repository.INotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(private val api: IParkItApi) :
    INotificationRepository {
    override suspend fun getNotifications(
        pageNo: Int,
        pageSize: Int
    ): NotificationListResponseDto {
        return api.getNotifications(pageNo, pageSize)
    }
}