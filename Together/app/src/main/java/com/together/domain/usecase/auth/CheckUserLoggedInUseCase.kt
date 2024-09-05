package com.together.domain.usecase.auth

interface CheckUserLoggedInUseCase {
    suspend fun checkUserLogged() : Boolean
}