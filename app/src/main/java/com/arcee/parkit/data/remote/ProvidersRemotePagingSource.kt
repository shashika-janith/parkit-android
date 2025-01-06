package com.arcee.parkit.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arcee.parkit.data.remote.dto.toProvider
import com.arcee.parkit.domain.model.Provider
import retrofit2.HttpException
import java.io.IOException

class ProvidersRemotePagingSource(private val api: IParkItApi) : PagingSource<Int, Provider>() {
    override fun getRefreshKey(state: PagingState<Int, Provider>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Provider> {
        try {
            val page = params.key ?: 1;
            val response = api.getProviders(page, params.loadSize)

            val providers = response.data.map { it.toProvider() }
            val nextKey = if (response.data.isNotEmpty()) page + 1 else null

            return LoadResult.Page(
                data = providers,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }
}