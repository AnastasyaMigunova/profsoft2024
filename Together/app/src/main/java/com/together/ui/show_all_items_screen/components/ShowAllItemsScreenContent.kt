package com.together.ui.show_all_items_screen.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.components.CustomCommunityNoteCard
import com.together.ui.components.CustomCourseCard
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.components.error.ErrorScreen
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.show_all_items_screen.ListType
import com.together.ui.show_all_items_screen.ShowItemsSideEffect
import com.together.ui.show_all_items_screen.ShowItemsViewModel
import com.together.ui.theme.LocalCustomColors
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ShowAllItemsScreenContent(
    type: String?,
    viewModel: ShowItemsViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (type != null) {
            viewModel.loadList(type)
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(sideEffect, context)
    }


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

        state.listCourses.isNotEmpty() -> {
            SuccessLoadingList(
                listCourses = state.listCourses,
                type = ListType.COURSE_TYPE
            )
        }

        state.listLocalNotes.isNotEmpty() -> {
            SuccessLoadingList(
                listLocalNotes = state.listLocalNotes,
                type = ListType.LOCAL_NOTE_TYPE
            )
        }

        state.listCommunityNotes.isNotEmpty() -> {
            SuccessLoadingList(
                listCommunityNotes = state.listCommunityNotes,
                type = ListType.COMMUNITY_NOTE_TYPE
            )
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

@Composable
fun SuccessLoadingList(
    listCourses: List<CourseVO> = emptyList(),
    listLocalNotes: List<LocalNoteVO> = emptyList(),
    listCommunityNotes: List<CommunityNoteVO> = emptyList(),
    type: String?
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (type) {
            ListType.COURSE_TYPE -> {
                items(listCourses) { course ->
                    CustomCourseCard(
                        title = course.title,
                        tags = course.tags
                    )
                }
            }

            ListType.LOCAL_NOTE_TYPE -> {
                items(listLocalNotes) { note ->
                    CustomLocalNoteCard(localNoteVO = note)
                }
            }

            ListType.COMMUNITY_NOTE_TYPE -> {
                items(listCommunityNotes) { note ->
                    CustomCommunityNoteCard(communityNoteVO = note)
                }
            }
        }
    }
}

private fun handleSideEffect(
    sideEffect: ShowItemsSideEffect,
    context: Context
) {
    when (sideEffect) {
        is ShowItemsSideEffect.ShowError -> {
            Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
        }
    }
}
