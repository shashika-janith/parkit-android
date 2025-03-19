package com.arcee.parkit.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arcee.parkit.data.remote.dto.FilterParkingAreasDto
import com.arcee.parkit.data.remote.dto.toProvider
import com.arcee.parkit.domain.model.Provider
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max

class ProvidersRemotePagingSource(
    private val api: IParkItApi,
    private val latitude: Double,
    private val longitude: Double
) : PagingSource<Int, Provider>() {
    private val STARTING_KEY = 1;

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, Provider>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Provider> {
        try {
            val page = params.key ?: STARTING_KEY;

            val reqPayload = FilterParkingAreasDto(
                pageNo = ensureValidKey(page),
                pageSize = params.loadSize,
                latitude = latitude,
                longitude = longitude
            )

            val response = api.getProviders(reqPayload)

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