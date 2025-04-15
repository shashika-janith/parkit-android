package com.arcee.parkit.data.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val NAME = stringPreferencesKey("name")
    val EMAIL = stringPreferencesKey("email")
    val PHONE = stringPreferencesKey("phone")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val EMAIL_VERIFIED = booleanPreferencesKey("email_verified")
    val PHONE_VERIFIED = booleanPreferencesKey("phone_verified")
}