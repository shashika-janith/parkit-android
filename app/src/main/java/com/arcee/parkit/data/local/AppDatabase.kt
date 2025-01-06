package com.arcee.parkit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProviderEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract val providerDao: ProviderDao
}