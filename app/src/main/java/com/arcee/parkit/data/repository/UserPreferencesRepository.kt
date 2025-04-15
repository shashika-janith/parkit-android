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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private val TAG = "UserPreferencesRepository"

    suspend fun fetchInitialPreferences() =
        mapUserPreferences(dataStore.data.first().toPreferences())

    suspend fun getAuthToken() = dataStore.data.first()[PreferencesKeys.ACCESS_TOKEN]

    /**
     * Get the user preferences flow.
     */
    val userPreferencesFlow: Flow<UserPreferences?> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "Error reading preferences.", exception)
            emit(emptyPreferences())
        }
    }.map { preferences ->
        mapUserPreferences(preferences)
    }

    /**
     * Saves the current user's information to the DataStore preferences.
     *
     * Stores essential user attributes such as name, phone, email, access token,
     * and verification status flags into the [dataStore] for persistent local storage.
     *
     * This function is `suspend` and should be called from a coroutine or another suspend function.
     *
     * @param user The [User] object containing the current user's details to persist.
     */
    suspend fun saveCurrentUser(user: User) = dataStore.edit { preferences ->
        preferences[PreferencesKeys.NAME] = user.name
        preferences[PreferencesKeys.PHONE] = user.phone
        preferences[PreferencesKeys.EMAIL] = user.email
        preferences[PreferencesKeys.ACCESS_TOKEN] = user.accessToken ?: ""
        preferences[PreferencesKeys.EMAIL_VERIFIED] = user.emailVerified
        preferences[PreferencesKeys.PHONE_VERIFIED] = user.phoneVerified
    }

    /**
     * Clears all stored preferences in the DataStore.
     *
     * This is a suspend function and must be called from a coroutine or another suspend function.
     *
     * @throws IOException if there is an issue accessing the DataStore.
     */
    suspend fun clearPreferences() = dataStore.edit { preferences -> preferences.clear() }


    /**
     * Maps a [Preferences] object to a [UserPreferences] instance.
     *
     * Extracts relevant user information such as name, phone, email, access token,
     * and verification flags from the provided [preferences] and returns a [UserPreferences]
     * object if all required values are present and valid.
     *
     * @param preferences The DataStore [Preferences] containing stored user values.
     * @return A fully populated [UserPreferences] object, or `null` if any required
     *         field is missing or invalid (i.e., blank or null).
     */
    private fun mapUserPreferences(preferences: Preferences): UserPreferences? {
        val name = preferences[PreferencesKeys.NAME]
        val phone = preferences[PreferencesKeys.PHONE]
        val email = preferences[PreferencesKeys.EMAIL]
        val accessToken = preferences[PreferencesKeys.ACCESS_TOKEN]
        val emailVerified = preferences[PreferencesKeys.EMAIL_VERIFIED]
        val phoneVerified = preferences[PreferencesKeys.PHONE_VERIFIED]

        if (name.isNullOrBlank()
            || phone.isNullOrBlank()
            || email.isNullOrBlank()
            || accessToken.isNullOrBlank()
            || emailVerified == null
            || phoneVerified == null
        ) return null

        return UserPreferences(
            name = name,
            phone = phone,
            email = email,
            accessToken = accessToken,
            emailVerified = emailVerified,
            phoneVerified = phoneVerified,
        )
    }
}