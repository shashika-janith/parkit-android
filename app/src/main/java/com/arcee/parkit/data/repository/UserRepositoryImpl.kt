package com.arcee.parkit.data.repository

import com.arcee.parkit.data.remote.IParkItApi
import com.arcee.parkit.data.remote.dto.SignInResponseDto
import com.arcee.parkit.data.remote.dto.SignInDto
import com.arcee.parkit.data.remote.dto.SignUpDto
import com.arcee.parkit.data.remote.dto.SignUpResponseDto
import com.arcee.parkit.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: IParkItApi) : IUserRepository {
    override suspend fun signIn(dto: SignInDto): SignInResponseDto {
        return api.signIn(dto)
    }

    override suspend fun signUp(dto: SignUpDto): SignUpResponseDto {
        return api.signUp(dto)
    }
}