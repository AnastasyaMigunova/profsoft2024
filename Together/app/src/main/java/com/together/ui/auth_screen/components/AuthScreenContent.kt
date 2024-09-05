package com.together.ui.auth_screen.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.auth_screen.AuthIntent
import com.together.ui.auth_screen.AuthSideEffect
import com.together.ui.auth_screen.AuthViewModel
import com.together.ui.components.CustomButton
import com.together.ui.components.CustomDescription
import com.together.ui.components.CustomImage
import com.together.ui.components.CustomTitle
import com.together.ui.components.TextField
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import com.together.ui.theme.textGray

@Composable
fun AuthScreenContent(
    navigateToRegistration: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current
    val customTypography = LocalCustomTypography.current
    val customColors = LocalCustomColors.current

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            handleSideEffect(sideEffect, navigateToMain, context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customColors.backgroundYellow)
            .padding(horizontal = 16.dp)
    ) {
        CustomImage(
            modifier = Modifier
                .padding(top = 70.dp)
                .align(Alignment.CenterHorizontally)
        )
        CustomTitle(
            modifier = Modifier
                .padding(top = 70.dp),
            text = stringResource(id = R.string.auth_title)
        )
        CustomDescription(
            text = stringResource(id = R.string.auth_description),
            modifier = Modifier
                .padding(top = 6.dp),
            style = customTypography.description.copy(
                color = textGray
            )
        ) {}
        TextField(text = phoneNumber, hintResId = R.string.phone_number)
        TextField(text = password, hintResId = R.string.password)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(id = R.string.text_enter),
            isLoading = state.isLoading,
        ) {
            viewModel.onIntent(AuthIntent.Authenticate(phoneNumber.value, password.value))
        }

        if (state.errorMessage != null) {
            Log.d("AUTH_ERROR", "Error: ${(state.errorMessage)}")
        }

        CustomDescription(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.register_title),
            customTypography.description.copy(fontWeight = FontWeight.Bold)
        ) { navigateToRegistration() }
    }
}

private fun handleSideEffect(
    sideEffect: AuthSideEffect,
    navigateToMain: () -> Unit,
    context: Context
) {
    when (sideEffect) {
        is AuthSideEffect.NavigateToMain -> {
            navigateToMain()
        }

        is AuthSideEffect.ShowError -> {
            Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
        }
    }
}
