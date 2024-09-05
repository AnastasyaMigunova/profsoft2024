package com.together.ui.main_screen.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.components.CustomCommunityNoteCard
import com.together.ui.components.CustomLineItems
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.components.CustomViewPager
import com.together.ui.components.error.ErrorScreen
import com.together.ui.main_screen.MainSideEffect
import com.together.ui.main_screen.MainViewModel
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.show_all_items_screen.ListType
import com.together.ui.theme.LocalCustomColors
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MainScreenContent(
    navigateToAllItems: (type: String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current
    val errorMessage = stringResource(id = R.string.error_message)

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(sideEffect, errorMessage, navigateToAllItems, context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = LocalCustomColors.current.backgroundYellow
                    )
                }
            }

            state.lastCourses.isNotEmpty() && state.lastCommunityNote != null -> {
                val lastCommunityNote = state.lastCommunityNote
                val lastLocalNote = state.lastLocalNote
                lastCommunityNote?.let { communityNote ->
                    SuccessLoadingData(state.lastCourses, communityNote, lastLocalNote) {
                        viewModel.navigate(it)
                    }
                }
            }

            state.errorMessage != null -> {
                val error = state.errorMessage
                ErrorScreen()
                if (error != null) {
                    Log.d("ERROR", error)
                }
            }
        }
    }
}

@Composable
private fun SuccessLoadingData(
    courses: List<CourseVO>,
    lastCommunityNote: CommunityNoteVO,
    lastLocalNote: LocalNoteVO?,
    navigateToAllItems: (type: String) -> Unit
) {
    CustomLineItems(title = stringResource(id = R.string.your_courses)) {
        navigateToAllItems(ListType.COURSE_TYPE)
    }
    CustomViewPager(courses = courses)

    CustomLineItems(title = stringResource(id = R.string.your_notes)) {
        navigateToAllItems(ListType.LOCAL_NOTE_TYPE)
    }
    CustomLocalNoteCard(localNoteVO = lastLocalNote)

    CustomLineItems(title = stringResource(id = R.string.community_notes)) {
        navigateToAllItems(ListType.COMMUNITY_NOTE_TYPE)
    }
    CustomCommunityNoteCard(communityNoteVO = lastCommunityNote)
}

private fun handleSideEffect(
    sideEffect: MainSideEffect,
    errorMessage: String,
    navigateToAllItems: (String) -> Unit,
    context: Context
) {
    when (sideEffect) {
        is MainSideEffect.ShowError -> {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
        is MainSideEffect.NavigateTo -> {
            navigateToAllItems(sideEffect.type)
        }
    }
}