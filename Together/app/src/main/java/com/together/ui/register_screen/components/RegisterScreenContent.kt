package com.together.ui.register_screen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.together.R
import com.together.ui.components.CustomButton
import com.together.ui.components.CustomDescription
import com.together.ui.components.CustomImage
import com.together.ui.components.CustomTitle
import com.together.ui.components.TextField
import com.together.ui.register_screen.RegisterSideEffect
import com.together.ui.register_screen.RegisterViewModel
import com.together.ui.theme.LocalCustomColors
import com.together.ui.theme.LocalCustomTypography
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun RegisterScreenContent(
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val name = rememberSaveable { mutableStateOf("") }
    val surname = rememberSaveable { mutableStateOf("") }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is RegisterSideEffect.NavigateToMain -> {
                    navigateToMain()
                }

                is RegisterSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCustomColors.current.backgroundYellow)
            .padding(16.dp)
    ) {
        CustomImage(
            modifier = Modifier
                .padding(top = 70.dp)
                .align(Alignment.CenterHorizontally)
        )
        CustomTitle(
            modifier = Modifier
                .padding(top = 58.dp),
            text = stringResource(id = R.string.register_title)
        )
        CustomDescription(
            modifier = Modifier
                .padding(top = 6.dp),
            text = stringResource(id = R.string.register_description)
        ) {}
        TextField(text = name, hintResId = R.string.name)
        TextField(text = surname, hintResId = R.string.surname)
        TextField(text = phoneNumber, hintResId = R.string.phone_number)
        TextField(text = password, hintResId = R.string.password)
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(id = R.string.register_title),
            isLoading = state.isLoading
        ) {
            viewModel.registration(
                phoneNumber.value,
                password.value,
                name.value,
                surname.value,
                avatar = ""
            )
        }
        CustomDescription(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.log_with_account),
            LocalCustomTypography.current.description.copy(fontWeight = FontWeight.Bold)
        ) { navigateToAuth() }
    }
}