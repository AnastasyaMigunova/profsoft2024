package com.example.lesson10.ui.profilescreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lesson10.R

@Composable
fun InfoProfile() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        CustomTwoTexts(
            title = stringResource(id = R.string.city_title),
            description = stringResource(id = R.string.city_value)
        )
        CustomTwoTexts(
            title = stringResource(id = R.string.about_me_title),
            description = stringResource(id = R.string.about_me_value)
        )
    }
}