package com.together.ui.splash_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.components.CustomImage
import com.together.ui.splash_screen.SplashSideEffect
import com.together.ui.splash_screen.SplashViewModel
import com.together.ui.theme.LocalCustomColors
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SplashScreenContent(
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsState()
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = FastOutSlowInEasing
            )
        )
        delay(1500)
    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(sideEffect, navigateToAuth, navigateToMain)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCustomColors.current.backgroundYellow)
    ) {
        CustomImage(
            modifier = Modifier.align(Alignment.Center)
        )

        CustomImage(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 58.dp),
            image = R.drawable.ic_together
        )
    }
}

private fun handleSideEffect(
    sideEffect: SplashSideEffect,
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit,
    ) {

    when (sideEffect) {
        SplashSideEffect.NavigateToAuth -> {
            navigateToAuth()
        }
        SplashSideEffect.NavigateToMain -> {
            navigateToMain()
        }
    }
}