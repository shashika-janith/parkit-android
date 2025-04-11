package com.arcee.parkit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arcee.parkit.data.remote.PagingSourceFactory
import com.arcee.parkit.domain.model.Favorite
import com.arcee.parkit.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val pagingSourceFactory: PagingSourceFactory) :
    IFavoriteRepository {
    override fun getPagedItems(): Flow<PagingData<Favorite>> {
        return Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { pagingSourceFactory.createFavouriteRemotePagingSource() }).flow
    }
}