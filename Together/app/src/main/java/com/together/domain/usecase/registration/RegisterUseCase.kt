package com.together.domain.usecase.registration

interface RegisterUseCase {
    suspend fun register(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ): Boolean
}