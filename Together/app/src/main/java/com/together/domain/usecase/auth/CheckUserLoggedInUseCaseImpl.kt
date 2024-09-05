package com.together.domain.usecase.auth

import com.together.data.storage.preferences.TokenManager
import javax.inject.Inject

class CheckUserLoggedInUseCaseImpl @Inject constructor(
    private val tokenManager: TokenManager
) : CheckUserLoggedInUseCase {
    override suspend fun checkUserLogged(): Boolean {
        return tokenManager.getToken() != null
    }
}