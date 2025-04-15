package com.arcee.parkit.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.arcee.parkit.common.Constants
import com.arcee.parkit.data.local.AppDatabase
import com.arcee.parkit.data.local.ProviderEntity
import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.remote.PagingSourceFactory
import com.arcee.parkit.data.remote.ProviderRemoteMediator
import com.arcee.parkit.data.repository.NotificationRepositoryImpl
import com.arcee.parkit.data.repository.ProviderRepositoryImpl
import com.arcee.parkit.data.repository.UserPreferencesRepository
import com.arcee.parkit.data.repository.UserRepositoryImpl
import com.arcee.parkit.domain.repository.INotificationRepository
import com.arcee.parkit.domain.repository.IProviderRepository
import com.arcee.parkit.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesParkItApi(authTokenProvider: AuthTokenProvider): IParkItApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authTokenProvider))
            .build()

        return Builder().baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IParkItApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthTokenProvider(userPreferencesRepository: UserPreferencesRepository): AuthTokenProvider {
        return AuthTokenProvider(userPreferencesRepository)
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
    fun providesProviderRepository(pagingSourceFactory: PagingSourceFactory): IProviderRepository {
        return ProviderRepositoryImpl(pagingSourceFactory)
    }

    @Provides
    @Singleton
    fun providesPagingSourceFactory(api: IParkItApi): PagingSourceFactory {
        return PagingSourceFactory(api)
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesProvidersPager(database: AppDatabase, api: IParkItApi): Pager<Int, ProviderEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ProviderRemoteMediator(
                database = database,
                api = api
            ),
            pagingSourceFactory = { database.providerDao.pagingSource() }
        )
    }
}