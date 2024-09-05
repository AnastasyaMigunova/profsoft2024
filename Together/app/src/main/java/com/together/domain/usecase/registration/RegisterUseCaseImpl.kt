package com.together.domain.usecase.registration

import com.together.domain.models.RegisterCredentials
import com.together.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val registerRepository: RegisterRepository
) : RegisterUseCase {
    override suspend fun register(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ): Boolean {
        return try {
            val credentials = RegisterCredentials(
                phoneNumber = phoneNumber,
                password = password,
                name = name,
                surname = surname,
                avatar = avatar
            )
            val token = registerRepository.register(credentials)
            true
        } catch (e: Exception) {
           false
        }
    }
}