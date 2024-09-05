package com.together.ui.favourites_screen.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.components.error.ErrorScreen
import com.together.ui.favourites_screen.FavouritesSideEffect
import com.together.ui.favourites_screen.FavouritesViewModel
import com.together.ui.models.LocalNoteVO
import com.together.ui.theme.LocalCustomColors
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun FavouritesScreenContent(
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current
    val errorMessage = stringResource(id = R.string.error_message)

    LaunchedEffect(Unit) {
        viewModel.loadLocalLastFavNote()
    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(sideEffect, errorMessage, context)
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

        state.localFavNotes.isNotEmpty() -> {
            val localFavNotes = state.localFavNotes
            SuccessLoadingData(localFavNotes)
        }

        state.localFavNotes.isEmpty() -> {
            val textEmptyList = stringResource(id = R.string.empty)

            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = textEmptyList)
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

@Composable
private fun SuccessLoadingData(
    localFavNotes: List<LocalNoteVO>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(localFavNotes) { note ->
            CustomLocalNoteCard(localNoteVO = note)
        }
    }
}

private fun handleSideEffect(
    sideEffect: FavouritesSideEffect,
    errorMessage: String,
    context: Context
) {
    when (sideEffect) {
        is FavouritesSideEffect.ShowError -> {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}