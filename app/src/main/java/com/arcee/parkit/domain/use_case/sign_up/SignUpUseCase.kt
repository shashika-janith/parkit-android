package com.arcee.parkit.domain.use_case.sign_up

import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.remote.dto.SignUpDto
import com.arcee.parkit.data.remote.dto.toUser
import com.arcee.parkit.domain.repository.IUserRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: IUserRepository) {
    operator fun invoke(dto: SignUpDto) = flow {
        try {
            emit(Resource.Loading())
            val result = repository.signUp(dto).data.toUser()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}