package com.arcee.parkit.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.arcee.parkit.common.Constants
import com.arcee.parkit.data.local.AppDatabase
import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.remote.ProvidersRemotePagingSource
import com.arcee.parkit.data.repository.NotificationRepositoryImpl
import com.arcee.parkit.data.repository.ProviderRepositoryImpl
import com.arcee.parkit.data.repository.UserPreferencesRepository
import com.arcee.parkit.data.repository.UserRepositoryImpl
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.domain.repository.INotificationRepository
import com.arcee.parkit.domain.repository.IProviderRepository
import com.arcee.parkit.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesParkItApi(): IParkItApi {
        return Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IParkItApi::class.java)
    }

    @Provides
    @Singleton
    fun providesUserRepository(api: IParkItApi): IUserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesNotificationRepository(api: IParkItApi): INotificationRepository {
        return NotificationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesProviderRepository(api: IParkItApi): IProviderRepository {
        return ProviderRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesUserPreferencesRepository(dataStore: DataStore<Preferences>): UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "parkit.db"
        ).build()
    }

//    @OptIn(ExperimentalPagingApi::class)
//    @Provides
//    @Singleton
//    fun providesProvidersPager(database: AppDatabase, api: IParkItApi): Pager<Int, ProviderEntity> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            remoteMediator = ProviderRemoteMediator(
//                database = database,
//                api = api
//            ),
//            pagingSourceFactory = { database.providerDao.pagingSource() }
//        )
//    }

    @Provides
    @Singleton
    fun providesProvidersPager(api: IParkItApi): Pager<Int, Provider> {
        return Pager(config = PagingConfig(pageSize = 20)) {
            ProvidersRemotePagingSource(api = api)
        }
    }
}