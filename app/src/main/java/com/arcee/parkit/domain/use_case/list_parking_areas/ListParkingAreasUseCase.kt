package com.arcee.parkit.domain.use_case.list_parking_areas

import androidx.paging.PagingData
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.domain.repository.IProviderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListParkingAreasUseCase @Inject constructor(private val repository: IProviderRepository) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<PagingData<Provider>> {
        return repository.getPagedItems(latitude, longitude)
    }
}