package com.together.ui.register_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.together.ui.register_screen.components.RegisterScreenContent

@Composable
fun RegisterScreen(
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit
) {
    RegisterScreenContent(navigateToAuth, navigateToMain)
}