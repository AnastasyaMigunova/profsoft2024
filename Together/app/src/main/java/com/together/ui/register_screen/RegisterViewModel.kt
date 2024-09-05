package com.together.ui.register_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.registration.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed class RegisterIntent {
    data class Registration(
        val phoneNumber: String,
        val password: String,
        val name: String,
        val surname: String,
        val avatar: String
    ) :
        RegisterIntent()
}

sealed class RegisterSideEffect {
    data object NavigateToMain : RegisterSideEffect()
    data class ShowError(val message: String) : RegisterSideEffect()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ContainerHost<RegisterState, RegisterSideEffect>, ViewModel() {

    override val container = container<RegisterState, RegisterSideEffect>(RegisterState())

    fun onIntent(intent: RegisterIntent) = intent {
        when (intent) {
            is RegisterIntent.Registration -> registration(
                intent.phoneNumber,
                intent.password,
                intent.name,
                intent.surname,
                intent.avatar
            )
        }
    }

    fun registration(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ) = intent {
        reduce { state.copy(isLoading = true) }

        try {
            val result =
                registerUseCase.register(phoneNumber, password, name, surname, avatar)

            if (result) {
                reduce {
                    state.copy(isLoading = false, isSuccess = true, errorMessage = null)
                }
                postSideEffect(RegisterSideEffect.NavigateToMain)
            } else {
                val error = "Ошибка авторизации"
                reduce {
                    state.copy(isLoading = false, isSuccess = false, errorMessage = error)
                }
                postSideEffect(RegisterSideEffect.ShowError(error))
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Неизвестная ошибка"
            reduce {
                state.copy(isLoading = false, isSuccess = false, errorMessage = errorMessage)
            }
            postSideEffect(RegisterSideEffect.ShowError(errorMessage))
        }
    }
}