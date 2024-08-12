package com.example.lesson10.ui.profilescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lesson10.R
import com.example.lesson10.ui.components.CustomButton
import com.example.lesson10.ui.components.CustomToolbar
import com.example.lesson10.ui.navigation.UserInfoKeys
import com.example.lesson10.ui.navigation.navigateToEditProfileScreen
import com.example.lesson10.ui.theme.Lesson10Theme
import com.example.lesson10.ui.theme.LocalCustomColors
import com.example.lesson10.ui.theme.LocalCustomTypography

@Composable
fun ProfileScreenContent(
    onBackClick: (() -> Unit)? = null,
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    dateOfBirth: String
) {
    val firstNameState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserInfoKeys.FIRST_NAME_KEY)?.observeAsState()
    val lastNameState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserInfoKeys.LAST_NAME_KEY)?.observeAsState()
    val patronymicState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserInfoKeys.PATRONYMIC_KEY)?.observeAsState()
    val dateOfBirthState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserInfoKeys.DATE_OF_BIRTH_KEY)?.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            CustomToolbar(
                title = stringResource(id = R.string.text_profile),
                onBackClick,
                LocalCustomColors.current.backgroundGray
            )
            CardProfile(
                firstName = firstNameState?.value ?: firstName,
                lastName = lastNameState?.value ?: lastName,
                patronymic = patronymicState?.value ?: patronymic,
                dateOfBirth = dateOfBirthState?.value ?: dateOfBirth
            )
            InfoProfile()
        }
        CustomButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.button_edit_profile),
            onClick = {
                navController.navigateToEditProfileScreen(
                    firstName = firstNameState?.value ?: firstName,
                    lastName = lastNameState?.value ?: lastName,
                    patronymic = patronymicState?.value ?: patronymic,
                    dateOfBirth = dateOfBirthState?.value ?: dateOfBirth
                )
            }
        )
    }
}

@Composable
fun CustomText(text: String, style: TextStyle = LocalCustomTypography.current.description) {
    Text(
        text = text,
        style = style
    )
}

@Composable
fun CustomTwoTexts(modifier: Modifier = Modifier, title: String, description: String) {
    Text(
        text = title,
        modifier = modifier
            .padding(top = 20.dp),
        style = LocalCustomTypography.current.description,
        color = LocalCustomColors.current.textGray
    )
    Text(
        text = description,
        modifier = modifier
            .padding(top = 8.dp),
        style = LocalCustomTypography.current.description
    )
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    Lesson10Theme {
        ProfileScreenContent(
            {},
            rememberNavController(),
            "Иван",
            "Иванов",
            "Иванович",
            "01.01.2001"
        )
    }
}