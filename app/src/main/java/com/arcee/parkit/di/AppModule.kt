package com.arcee.parkit.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.arcee.parkit.common.Constants
import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.repository.NotificationRepositoryImpl
import com.arcee.parkit.data.repository.UserPreferencesRepository
import com.arcee.parkit.data.repository.UserRepositoryImpl
import com.arcee.parkit.domain.repository.INotificationRepository
import com.arcee.parkit.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providesUserPreferencesRepository(dataStore: DataStore<Preferences>): UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }
}