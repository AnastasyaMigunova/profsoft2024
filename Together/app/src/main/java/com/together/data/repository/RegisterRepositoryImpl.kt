package com.together.data.repository

import com.together.data.api.ApiService
import com.together.data.models.registration.RegisterRequestInfo
import com.together.data.storage.preferences.TokenManager
import com.together.domain.models.RegisterCredentials
import com.together.domain.repository.RegisterRepository
import com.together.utils.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : RegisterRepository {
    override suspend fun register(registerCredentials: RegisterCredentials): Boolean {
        return try {
            val hashedPassword = Utils.hashPassword(registerCredentials.password)
            val response =
                apiService.registerUser(
                    RegisterRequestInfo(
                        registerCredentials.phoneNumber,
                        hashedPassword,
                        registerCredentials.name,
                        registerCredentials.surname,
                        registerCredentials.avatar
                    )
                )

            response.data?.let {
                tokenManager.saveToken(it.token)
                true
            } ?: false
        } catch (e: HttpException) {
            throw Exception("Ошибка сервера: ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Ошибка сервера: ${e.message}")
        }
    }
}