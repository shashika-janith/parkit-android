package com.arcee.parkit.data.remote

import com.arcee.parkit.data.remote.dto.SignInResponseDto
import com.arcee.parkit.data.remote.dto.NotificationListResponseDto
import com.arcee.parkit.data.remote.dto.SignInDto
import com.arcee.parkit.data.remote.dto.SignUpDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IParkItApi {
    @POST("/user/sign-in")
    suspend fun signIn(@Body dto: SignInDto): SignInResponseDto

    @POST("/user/sign-up")
    suspend fun signUp(@Body dto: SignUpDto): Void

    @GET("/notifications")
    suspend fun getNotifications(@Query("pageNo") pageNo: Int, @Query("pageSize") pageSize: Int): NotificationListResponseDto
}