package com.arcee.parkit.data.preferences

data class UserPreferences(
    val name: String,
    val email: String,
    val phone: String,
    val accessToken: String,
    val emailVerified: Boolean,
    val phoneVerified: Boolean
)
