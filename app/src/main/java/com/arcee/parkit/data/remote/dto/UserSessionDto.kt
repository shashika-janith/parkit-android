package com.arcee.parkit.data.remote.dto

import com.arcee.parkit.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserSessionDto(
    @SerializedName("user_id")
    val userId: Int,
    val name: String,
    val email: String,
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
        accessToken = accessToken,
        roles = roles
    )
}