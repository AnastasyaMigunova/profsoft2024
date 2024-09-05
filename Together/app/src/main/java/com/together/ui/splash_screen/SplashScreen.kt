package com.together.ui.splash_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.together.ui.navigation.navigateToAuthScreen
import com.together.ui.splash_screen.components.SplashScreenContent

@Composable
fun SplashScreen(
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit
) {
    SplashScreenContent(navigateToAuth, navigateToMain)
}