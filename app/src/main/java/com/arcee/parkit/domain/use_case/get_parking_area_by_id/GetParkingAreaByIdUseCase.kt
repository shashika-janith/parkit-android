package com.arcee.parkit.domain.use_case.get_parking_area_by_id

import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.remote.dto.toProvider
import com.arcee.parkit.domain.repository.IProviderRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetParkingAreaByIdUseCase @Inject constructor(private val repository: IProviderRepository) {
    operator fun invoke(id: Int) = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getProviderById(id).data.toProvider()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}