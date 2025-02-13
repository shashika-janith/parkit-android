package com.arcee.parkit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationPermissionViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableLiveData(PermissionStatus.PENDING)
    val state: LiveData<PermissionStatus> = _state

    fun updatePermissionStatus(isGranted: PermissionStatus) {
        _state.value = isGranted
    }

    fun requestLocationPermission() {
        _state.value = PermissionStatus.PENDING
    }
}