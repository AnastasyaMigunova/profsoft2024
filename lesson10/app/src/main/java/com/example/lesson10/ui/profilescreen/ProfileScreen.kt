package com.example.lesson10.ui.profilescreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lesson10.ui.navigation.navigateToEditProfileScreen
import com.example.lesson10.ui.profilescreen.components.ProfileScreenContent

@Composable
fun ProfileScreen(
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    dateOfBirth: String
) {
    ProfileScreenContent (
        onBackClick = navController::popBackStack,
        navController = navController,
        firstName = firstName,
        lastName = lastName,
        patronymic = patronymic,
        dateOfBirth = dateOfBirth
    )
}