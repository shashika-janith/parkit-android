package com.arcee.parkit.data.preferences

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val NAME = stringPreferencesKey("name")
    val EMAIL = stringPreferencesKey("email")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
}