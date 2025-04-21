package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.User

data class UserSessionDto(
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val accessToken: String
)

fun UserSessionDto.toUser(): User {
    return User(
        id = userId,
        name = "$firstName $lastName",
        email = email,
        phone = phone,
        emailVerified = false,
        phoneVerified = false,
        accessToken = accessToken,
        roles = listOf(),
    )
}