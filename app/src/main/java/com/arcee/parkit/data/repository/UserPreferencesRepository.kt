package com.arcee.parkit.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.arcee.parkit.data.preferences.PreferencesKeys
import com.arcee.parkit.data.preferences.UserPreferences
import com.arcee.parkit.domain.model.User
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private val TAG = "UserPreferencesRepository"

    suspend fun fetchInitialPreferences() =
        mapUserPreferences(dataStore.data.first().toPreferences())

    /**
     * Get the user preferences flow.
     */
    val userPreferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    suspend fun saveCurrentUser(user: User) = dataStore.edit { preferences ->
        preferences[PreferencesKeys.NAME] = user.name
        preferences[PreferencesKeys.EMAIL] = user.email
        preferences[PreferencesKeys.ACCESS_TOKEN] = user.accessToken ?: ""
    }

    suspend fun clearPreferences() = dataStore.edit { preferences -> preferences.clear() }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences? {
        val name = preferences[PreferencesKeys.NAME]
        val email = preferences[PreferencesKeys.EMAIL]
        val accessToken = preferences[PreferencesKeys.ACCESS_TOKEN]

        if (name.isNullOrBlank() || email.isNullOrBlank() || accessToken.isNullOrBlank())
            return null

        return UserPreferences(name, email, accessToken)
    }
}