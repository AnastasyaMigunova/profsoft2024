package com.together.ui.auth_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.together.ui.auth_screen.components.AuthScreenContent
import com.together.ui.navigation.navigateToRegisterScreen

@Composable
fun AuthScreen(
    navigateToRegistration: () -> Unit,
    navigateToMain: () -> Unit
) {
    AuthScreenContent(navigateToRegistration, navigateToMain)
}