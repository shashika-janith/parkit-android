package com.arcee.parkit.domain.repository

import com.arcee.parkit.data.remote.dto.SignInDto
import com.arcee.parkit.data.remote.dto.SignInResponseDto
import com.arcee.parkit.data.remote.dto.SignUpDto
import com.arcee.parkit.data.remote.dto.SignUpResponseDto

interface IUserRepository {
    suspend fun signIn(dto: SignInDto): SignInResponseDto

    suspend fun signUp(dto: SignUpDto): SignUpResponseDto
}