package com.together.domain.usecase.auth

import com.together.domain.models.AuthCredentials
import com.together.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) :
    AuthUseCase {
    override suspend fun auth(phoneNumber: String, password: String): Boolean {
        return try {
            val credentials = AuthCredentials(phoneNumber = phoneNumber, password = password)
            authRepository.auth(credentials)
            true
        } catch (e: Exception) {
           false
        }
    }
}