package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("user_id")
    val userId: Int,
    val name: String,
    val phone: String,
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean,
)

fun UserDto.toUser(): User {
    return User(
        id = userId,
        name = name,
        email = email,
        phone = phone,
        emailVerified = emailVerified,
        phoneVerified = phoneVerified,
        roles = null,
        accessToken = null
    )
}