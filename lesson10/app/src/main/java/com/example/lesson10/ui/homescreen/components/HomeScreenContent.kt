package com.example.lesson10.ui.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lesson10.R
import com.example.lesson10.ui.components.CustomButton
import com.example.lesson10.ui.components.CustomToolbar
import com.example.lesson10.ui.navigation.navigateToProfileScreen
import com.example.lesson10.ui.theme.Lesson10Theme

@Composable
fun HomeScreenContent(
    buttonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomToolbar(title = stringResource(id = R.string.text_main))
        CustomButton(
            text = stringResource(id = R.string.text_profile),
            onClick = buttonClick
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    Lesson10Theme {
        HomeScreenContent {}
    }
}