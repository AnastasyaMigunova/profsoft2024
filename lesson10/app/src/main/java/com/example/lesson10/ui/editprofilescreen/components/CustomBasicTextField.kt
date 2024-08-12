package com.example.lesson10.ui.editprofilescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lesson10.R
import com.example.lesson10.ui.theme.LocalCustomTypography

@Composable
fun CustomBasicTextField(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    patronymic: MutableState<String>,
    dateOfBirth: MutableState<String>
) {
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EditProfileText(
            text = stringResource(id = R.string.first_name),
            textState = firstName
        )
        EditProfileText(
            text = stringResource(id = R.string.last_name),
            textState = lastName
        )
        EditProfileText(
            text = stringResource(id = R.string.patronymic),
            textState = patronymic
        )
        EditProfileText(
            text = stringResource(id = R.string.date_of_birth),
            textState = dateOfBirth,
            inputType = "date"
        )
    }
}

@Composable
fun EditProfileText(
    text: String,
    textState: MutableState<String>,
    inputType: String = "text"
) {
    Row {
        Text(
            text = text,
            style = LocalCustomTypography.current.title
        )
        if (inputType == "text") {
            TextField(textState = textState)
        } else {
            DateTextField(textState = textState)
        }
    }

}

@Composable
fun TextField(textState: MutableState<String>) {
    BasicTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        textStyle = LocalCustomTypography.current.title
    )
}

@Composable
fun DateTextField(textState: MutableState<String>) {
    BasicTextField(
        value = textState.value,
        onValueChange = {
            if (it.length <= 10) {
                val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                textState.value = filteredText
            }
        },
        textStyle = LocalCustomTypography.current.title,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}