package com.together.data.repository

import com.together.data.api.ApiService
import com.together.data.models.auth.AuthRequestInfo
import com.together.data.storage.preferences.TokenManager
import com.together.domain.models.AuthCredentials
import com.together.domain.repository.AuthRepository
import com.together.utils.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun auth(authCredentials: AuthCredentials): Boolean {
        return try {
            val hashedPassword = Utils.hashPassword(authCredentials.password)
            val response =
                apiService.authUser(AuthRequestInfo(authCredentials.phoneNumber, hashedPassword))

            response.data?.let {
                tokenManager.saveToken(it.token)
                true
            } ?: false

        } catch (e: HttpException) {
            throw Exception("Ошибка сервера: ${e.message}")
        } catch (e: IOException) {
            throw Exception("Ошибка сервера: ${e.message}")
        }
    }
}