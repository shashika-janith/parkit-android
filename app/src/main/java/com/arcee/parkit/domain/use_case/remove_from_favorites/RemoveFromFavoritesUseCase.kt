package com.arcee.parkit.domain.use_case.remove_from_favorites

import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.remote.dto.toProvider
import com.arcee.parkit.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(private val repository: IFavoriteRepository) {
    operator fun invoke(id: Long) = flow {
        try {
            emit(Resource.Loading())
            val result = repository.removeFromFavorites(id).data.toProvider()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}