package com.together.domain.usecase.auth

interface AuthUseCase {
    suspend fun auth(phoneNumber: String, password: String): Boolean
}