package com.arcee.parkit.domain.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val emailVerified: Boolean,
    val phoneVerified: Boolean,
    val roles: List<String>?,
    val accessToken: String?
)
