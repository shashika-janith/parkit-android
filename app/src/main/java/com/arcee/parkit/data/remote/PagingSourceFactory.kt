package com.arcee.parkit.data.remote

import javax.inject.Inject

class PagingSourceFactory @Inject constructor(private val api: IParkItApi) {
    fun createProviderRemotePagingSource(
        latitude: Double,
        longitude: Double
    ): ProvidersRemotePagingSource {
        return ProvidersRemotePagingSource(api, latitude, longitude)
    }

    fun createFavouriteRemotePagingSource(): FavouriteRemotePagingSource {
        return FavouriteRemotePagingSource(api)
    }
}