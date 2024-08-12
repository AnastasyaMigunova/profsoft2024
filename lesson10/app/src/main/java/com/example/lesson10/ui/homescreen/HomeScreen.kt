package com.example.lesson10.ui.homescreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lesson10.ui.homescreen.components.HomeScreenContent
import com.example.lesson10.ui.navigation.navigateToProfileScreen

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeScreenContent {navController.navigateToProfileScreen(
        firstName = "Иван",
        lastName = "Иванов",
        patronymic = "Иванович",
        dateOfBirth = "01.01.2001"
    )}
}