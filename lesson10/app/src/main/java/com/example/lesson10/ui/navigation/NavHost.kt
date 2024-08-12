package com.example.lesson10.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: String
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(navController)
        profileScreen(navController)
        editProfileScreen(navController)
    }
}