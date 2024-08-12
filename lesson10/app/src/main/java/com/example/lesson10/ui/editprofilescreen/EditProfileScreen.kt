package com.example.lesson10.ui.editprofilescreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lesson10.ui.editprofilescreen.components.EditProfileScreenContent

@Composable
fun EditProfileScreen(
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    dateOfBirth: String
    ) {
    EditProfileScreenContent (
        onBackClick = navController::popBackStack,
        navController = navController,
        firstName = firstName,
        lastName = lastName,
        patronymic = patronymic,
        dateOfBirth = dateOfBirth
    )
}