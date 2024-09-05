package com.together.ui.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.main_screen.components.MainScreenContent
import com.together.ui.topBarParams.TopBarParams

@Composable
fun MainScreen(
    navigateToAllItems: (type: String) -> Unit,
    topBarParams: MutableState<TopBarParams>,
    setBottomBarVisibility: (Boolean) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        topBarParams.value = topBarParams.value.copy(
            title = R.string.main_title,
            iconId = R.drawable.ic_search,
            height = 60.dp,
            visibility = true,
            onBackClick = null
        )
        setBottomBarVisibility(true)
    }

    MainScreenContent(navigateToAllItems)
}