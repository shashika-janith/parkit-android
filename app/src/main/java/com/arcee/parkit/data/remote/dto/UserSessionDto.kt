package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserSessionDto(
    @SerializedName("user_id")
    val userId: Int,
    val name: String,
    val email: String,
    val phone: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    val roles: List<String>
)

fun UserSessionDto.toUser(): User {
    return User(
        id = userId,
        name = name,
        email = email,
        phone = phone,
        emailVerified = emailVerified,
        phoneVerified = phoneVerified,
        accessToken = accessToken,
        roles = roles,
    )
}