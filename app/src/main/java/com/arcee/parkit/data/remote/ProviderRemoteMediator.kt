package com.arcee.parkit.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.arcee.parkit.data.local.AppDatabase
import com.arcee.parkit.data.local.ProviderEntity
import com.arcee.parkit.data.mappers.toProviderEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProviderRemoteMediator(
    private val database: AppDatabase,
    private val api: IParkItApi
) : RemoteMediator<Int, ProviderEntity>() {
    override suspend fun initialize(): InitializeAction {
        return super.initialize()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProviderEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val response = api.getProviders(pageNo = state.anchorPosition?.plus(1) ?: 0, pageSize = state.config.pageSize)
            lateinit var providerEntities: List<ProviderEntity>

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.providerDao.clearAll()
                }

                providerEntities = response.data.map { it.toProviderEntity() }
                database.providerDao.upsertAll(providerEntities)
            }

            val endOfPaginationReached = providerEntities.isEmpty()

            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached,

            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}