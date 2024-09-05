package com.together.ui.auth_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val MIN_PASSWORD_LENGTH = 6

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed class AuthIntent {
    data class Authenticate(val phoneNumber: String, val password: String) :
        AuthIntent()
}

sealed class AuthSideEffect {
    data object NavigateToMain : AuthSideEffect()
    data class ShowError(val message: String) : AuthSideEffect()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUsecase: AuthUseCase
) : ContainerHost<AuthState, AuthSideEffect>, ViewModel() {

    override val container = container<AuthState, AuthSideEffect>(AuthState())

    fun onIntent(intent: AuthIntent) = intent {
        when (intent) {
            is AuthIntent.Authenticate -> authenticate(intent.phoneNumber, intent.password)
        }
    }

    private fun authenticate(phoneNumber: String, password: String) = intent {
        val phoneNumberValidationError = validatePhoneNumber(phoneNumber)
        val passwordValidationError = validatePassword(password)

        if (!phoneNumberValidationError) {
            val phoneError = "Номер телефона введен в неверном формате"
            reduce {
                state.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = phoneError
                )
            }
            postSideEffect(AuthSideEffect.ShowError(phoneError))
            return@intent
        }

        if (!passwordValidationError) {
            val passwordError = "Пароль должен содержать больше 8 символов"
            reduce {
                state.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = passwordError
                )
            }
            postSideEffect(AuthSideEffect.ShowError(passwordError))
            return@intent
        }

        reduce { state.copy(isLoading = true) }

        try {
            val result = authUsecase.auth(phoneNumber, password)

            if (result) {
                reduce {
                    state.copy(isLoading = false, isSuccess = true, errorMessage = null)
                }
                postSideEffect(AuthSideEffect.NavigateToMain)
            } else {
                val error = "Ошибка авторизации"
                reduce {
                    state.copy(isLoading = false, isSuccess = false, errorMessage = error)
                }
                postSideEffect(AuthSideEffect.ShowError(error))
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Неизвестная ошибка"
            reduce {
                state.copy(isLoading = false, isSuccess = false, errorMessage = errorMessage)
            }
            postSideEffect(AuthSideEffect.ShowError(errorMessage))
        }
    }

    private fun validatePhoneNumber(phone: String): Boolean {
        val cleanedPhone = phone.replace("\\s|-".toRegex(), "")
        return when {
            cleanedPhone.startsWith("+7") && cleanedPhone.length == 12 -> true
            (cleanedPhone.startsWith("8") || cleanedPhone.startsWith("7")) && cleanedPhone.length == 11 -> true
            else -> false
        }
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }

//    fun validateName(name: String): Boolean {
//        return name.isNotBlank() && name.length >= 2
//    }
}