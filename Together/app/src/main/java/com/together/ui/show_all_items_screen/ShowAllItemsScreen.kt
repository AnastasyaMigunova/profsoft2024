package com.together.ui.show_all_items_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.together.R
import com.together.ui.show_all_items_screen.components.ShowAllItemsScreenContent
import com.together.ui.topBarParams.TopBarParams

@Composable
fun ShowAllItemsScreen(
    topBarParams: MutableState<TopBarParams>,
    type: String?,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        topBarParams.value = topBarParams.value.copy(
            title = when (type) {
                ListType.COURSE_TYPE -> R.string.all_courses
                ListType.LOCAL_NOTE_TYPE -> R.string.your_notes
                ListType.COMMUNITY_NOTE_TYPE -> R.string.community_notes
                else -> R.string.notes
            },
            iconId = R.drawable.ic_search,
            onBackClick = onBackClick
        )
    }

    ShowAllItemsScreenContent(
        type = type
    )
}