package com.arcee.parkit.presentation.notifications

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.remote.dto.toNotification
import com.arcee.parkit.domain.model.Notification
import com.arcee.parkit.domain.use_case.list_notifications.ListNotificationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val getNotificationsUseCase: ListNotificationsUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(NotificationsState())
    val state: State<NotificationsState> = _state

    init {
        getNotifications(0, 10)
    }

    fun getNotifications(pageNo: Int, pageSize: Int) {
        getNotificationsUseCase(pageNo, pageSize).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NotificationsState(isLoading = false)
                    val notificationDtos = result.data?.data

                    val notificationList: MutableList<Notification> = mutableListOf()

                    notificationDtos?.let {
                        for (notificationDto in it) {
                            notificationList.add(notificationDto.toNotification())
                        }

                        _state.value =
                            NotificationsState(notifications = notificationList)
                    }
                }

                is Resource.Error -> {
                    _state.value =
                        NotificationsState(
                            error = result.message ?: "An unexpected error occurred.",
                        )
                }

                is Resource.Loading -> {
                    _state.value = NotificationsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}