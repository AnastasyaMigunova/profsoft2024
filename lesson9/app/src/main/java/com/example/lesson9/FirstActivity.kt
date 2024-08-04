package com.example.lesson9

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesson9.components.CustomButton
import com.example.lesson9.ui.theme.Lesson9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lesson9Theme {
                FirstScreen()
            }
        }
    }
}

@Composable
fun FirstScreen() {
    val currentContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val firstText = stringResource(id = R.string.button_second_activity)
        CustomText()
        CustomButton(firstText) {
            val intent = Intent(currentContext, SecondActivity::class.java)
            currentContext.startActivity(intent)
        }
    }
}

@Composable
fun CustomText() {
    Text(
        text = stringResource(id = R.string.hello_world),
        modifier = Modifier.padding(bottom = 40.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    Lesson9Theme {
        FirstScreen()
    }
}