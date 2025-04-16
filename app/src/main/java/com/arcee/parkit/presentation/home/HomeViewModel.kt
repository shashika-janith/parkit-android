package com.arcee.parkit.presentation.home

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.arcee.parkit.common.Resource
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.domain.use_case.list_parking_areas.ListParkingAreasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listParkingAreasUseCase: ListParkingAreasUseCase
) : ViewModel() {

    private val _locationFilter = MutableStateFlow<Location?>(null)

    val providers: StateFlow<Resource<Flow<PagingData<Provider>>>> =
        _locationFilter.flatMapLatest { location ->
            flow {
                emit(Resource.Loading())
                try {
                    val pagingDataFlow =
                        listParkingAreasUseCase(
                            location?.latitude ?: 0.0,
                            location?.longitude ?: 0.0
                        )
                    emit(Resource.Success(pagingDataFlow))
                } catch (e: Exception) {
                    emit(Resource.Error(message = e.message ?: "Unknown error"))
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Resource.Loading()
        )
}