package com.example.lesson10.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lesson10.ui.editprofilescreen.EditProfileScreen

const val EDIT_PROFILE_SCREEN_ROUTE = "edit_profile_screen"
private const val TRANSITION_DURATION = 300
private const val FIRST_NAME_KEY = UserInfoKeys.FIRST_NAME_KEY
private const val LAST_NAME_KEY = UserInfoKeys.LAST_NAME_KEY
private const val PATRONYMIC_KEY = UserInfoKeys.PATRONYMIC_KEY
private const val DATE_OF_BIRTH_KEY = UserInfoKeys.DATE_OF_BIRTH_KEY

fun NavController.navigateToEditProfileScreen(
    firstName: String?,
    lastName: String?,
    patronymic: String?,
    dateOfBirth: String?
) {
    this.navigate("$EDIT_PROFILE_SCREEN_ROUTE/$firstName/$lastName/$patronymic/$dateOfBirth") {
        popUpTo(EDIT_PROFILE_SCREEN_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.editProfileScreen(
    navController: NavController
) {
    composable(
        route = "$EDIT_PROFILE_SCREEN_ROUTE/{$FIRST_NAME_KEY}/{$LAST_NAME_KEY}/{$PATRONYMIC_KEY}/{$DATE_OF_BIRTH_KEY}",
        arguments = listOf(
            navArgument(FIRST_NAME_KEY) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(LAST_NAME_KEY) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(PATRONYMIC_KEY) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(DATE_OF_BIRTH_KEY) {
                type = NavType.StringType
                nullable = true
            }
        ),
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) { backStackEntry ->
        val firstNameArg = backStackEntry.arguments?.getString(FIRST_NAME_KEY) ?: ""
        val lastNameArg = backStackEntry.arguments?.getString(LAST_NAME_KEY) ?: ""
        val patronymicArg = backStackEntry.arguments?.getString(PATRONYMIC_KEY) ?: ""
        val dateOfBirthArg = backStackEntry.arguments?.getString(DATE_OF_BIRTH_KEY) ?: ""

        EditProfileScreen(
            navController = navController,
            firstName = firstNameArg,
            lastName = lastNameArg,
            patronymic = patronymicArg,
            dateOfBirth = dateOfBirthArg
        )
    }
}