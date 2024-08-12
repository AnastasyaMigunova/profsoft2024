package com.example.lesson10.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.lesson10.ui.homescreen.HomeScreen

const val HOME_SCREEN_ROUTE = "home_screen"
private const val TRANSITION_DURATION = 300

fun NavController.navigateToHomeScreen() {
    this.navigate(HOME_SCREEN_ROUTE)
}

fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable(
        route = HOME_SCREEN_ROUTE,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        HomeScreen(
            navController = navController
        )
    }
}