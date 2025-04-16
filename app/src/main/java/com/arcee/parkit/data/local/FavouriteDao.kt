package com.arcee.parkit.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavouriteDao {
    @Upsert
    suspend fun upsertAll(favourites: List<FavouriteEntity>)

    @Query("DELETE FROM providers")
    suspend fun clearAll()

    @Query("SELECT * FROM providers")
    fun pagingSource(): PagingSource<Int, FavouriteEntity>
}