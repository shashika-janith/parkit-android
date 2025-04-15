package com.arcee.parkit.di

import com.arcee.parkit.data.repository.UserPreferencesRepository
import kotlinx.coroutines.runBlocking

class AuthTokenProvider(private val userPreferencesRepository: UserPreferencesRepository) {
    fun getTokenBlocking(): String? {
        return runBlocking {
            userPreferencesRepository.getAuthToken()
        }
    }
}