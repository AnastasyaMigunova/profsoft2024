package com.together.ui.profile_screen.components

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.components.CustomLineItems
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.components.CustomProfileCard
import com.together.ui.components.CustomViewPager
import com.together.ui.components.error.ErrorScreen
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.models.ProfileVO
import com.together.ui.profile_screen.ProfileSideEffect
import com.together.ui.profile_screen.ProfileViewModel
import com.together.ui.theme.LocalCustomColors
import com.together.utils.Utils.fillWidthOfParent
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ProfileScreenContent(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(sideEffect, context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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

            state.profile != null && state.lastCourses.isNotEmpty() -> {
                val lastLocalNote = state.lastLocalNote
                state.profile?.let { profile ->
                    SuccessLoadingProfile(profile, state.lastCourses, lastLocalNote)
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
fun SuccessLoadingProfile(
    profile: ProfileVO,
    courses: List<CourseVO>,
    lastLocalNoteVO: LocalNoteVO?
) {
    CustomProfileCard(
        modifier = Modifier.fillWidthOfParent(16.dp),
        profile = profile)

    CustomLineItems(title = stringResource(id = R.string.your_courses)){}
    CustomViewPager(courses = courses)

    CustomLineItems(title = stringResource(id = R.string.your_notes)){}
    CustomLocalNoteCard(localNoteVO = lastLocalNoteVO)
}


private fun handleSideEffect(
    sideEffect: ProfileSideEffect,
    context: Context
) {
    when (sideEffect) {
        is ProfileSideEffect.ShowError -> {
            Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreenContent() {
    ProfileScreenContent()
}