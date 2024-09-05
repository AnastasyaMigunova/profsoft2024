package com.together.ui.favourites_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.favourites_screen.components.FavouritesScreenContent
import com.together.ui.topBarParams.TopBarParams

@Composable
fun FavouritesScreen(
    topBarParams: MutableState<TopBarParams>
) {
    LaunchedEffect(key1 = Unit) {
        topBarParams.value = topBarParams.value.copy(
            title = R.string.favourite,
            iconId = R.drawable.ic_search,
            height = 60.dp,
            visibility = true
        )
    }

    FavouritesScreenContent()
}