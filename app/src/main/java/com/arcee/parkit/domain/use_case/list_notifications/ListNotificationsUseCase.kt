package com.arcee.parkit.domain.use_case.list_notifications

import com.arcee.parkit.common.Resource
import com.arcee.parkit.domain.repository.INotificationRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListNotificationsUseCase @Inject constructor(private val repository: INotificationRepository) {
    operator fun invoke(pageNo: Int, pageSize: Int) = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getNotifications(pageNo, pageSize)
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}