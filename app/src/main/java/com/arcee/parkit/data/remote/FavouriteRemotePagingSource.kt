package com.arcee.parkit.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arcee.parkit.data.remote.dto.toFavorite
import com.arcee.parkit.domain.model.Favorite
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max

class FavouriteRemotePagingSource(
    private val api: IParkItApi,
) : PagingSource<Int, Favorite>() {
    private val STARTING_KEY = 1;

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, Favorite>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Favorite> {
        try {
            val page = params.key ?: STARTING_KEY;

            val response = api.getFavorites(ensureValidKey(page), params.loadSize)

            val favorites = response.data.map { it.toFavorite() }
            val nextKey = if (response.data.isNotEmpty()) page + 1 else null

            return LoadResult.Page(
                data = favorites,
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