package com.together.ui.profile_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.profile_screen.components.ProfileScreenContent
import com.together.ui.topBarParams.TopBarParams

@Composable
fun ProfileScreen(
    topBarParams: MutableState<TopBarParams>
) {
    LaunchedEffect(key1 = Unit) {
        topBarParams.value = topBarParams.value.copy(
            title = R.string.profile,
            iconId = R.drawable.ic_users,
            height = 60.dp
        )
    }

    ProfileScreenContent()
}