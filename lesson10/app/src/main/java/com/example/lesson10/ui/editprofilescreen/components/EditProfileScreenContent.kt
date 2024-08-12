package com.example.lesson10.ui.editprofilescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lesson10.R
import com.example.lesson10.ui.components.CustomButton
import com.example.lesson10.ui.components.CustomToolbar
import com.example.lesson10.ui.navigation.UserInfoKeys
import com.example.lesson10.ui.theme.Lesson10Theme

@Composable
fun EditProfileScreenContent(
    onBackClick: (() -> Unit)? = null,
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    dateOfBirth: String
) {
    val firstNameState = rememberSaveable { mutableStateOf(firstName) }
    val lastNameState = rememberSaveable { mutableStateOf(lastName) }
    val patronymicState = rememberSaveable { mutableStateOf(patronymic) }
    val dateOfBirthState = rememberSaveable { mutableStateOf(dateOfBirth) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column {
            CustomToolbar(
                title = stringResource(
                    id = R.string.text_edit_profile
                ),
                onBackClick
            )
            CustomBasicTextField(
                firstName = firstNameState,
                lastName = lastNameState,
                patronymic = patronymicState,
                dateOfBirth = dateOfBirthState
            )
        }
        CustomButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.text_save)
        ) {
            navController.previousBackStackEntry?.savedStateHandle?.set(
                UserInfoKeys.FIRST_NAME_KEY,
                firstNameState.value
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
                UserInfoKeys.LAST_NAME_KEY,
                lastNameState.value
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
                UserInfoKeys.PATRONYMIC_KEY,
                patronymicState.value
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
                UserInfoKeys.DATE_OF_BIRTH_KEY,
                dateOfBirthState.value
            )
            navController.popBackStack()
        }
    }
}

@Preview
@Composable
private fun EditProfileScreenPreview() {
    Lesson10Theme {
        EditProfileScreenContent(
            {},
            rememberNavController(),
            "Иван",
            "Иванов",
            "Иванович",
            "01.01.2001"
        )
    }
}