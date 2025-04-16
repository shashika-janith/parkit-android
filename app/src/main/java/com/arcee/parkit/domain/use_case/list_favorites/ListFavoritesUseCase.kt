package com.arcee.parkit.domain.use_case.list_favorites

import androidx.paging.PagingData
import com.arcee.parkit.domain.model.Favorite
import com.arcee.parkit.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListFavoritesUseCase @Inject constructor(private val repository: IFavoriteRepository) {
    operator fun invoke(): Flow<PagingData<Favorite>> {
        return repository.getPagedItems()
    }
}