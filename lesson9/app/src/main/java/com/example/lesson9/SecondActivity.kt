package com.example.lesson9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lesson9.ui.theme.Lesson9Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lesson9Theme {
                SecondScreen()
            }
        }
    }
}

@Composable
fun SecondScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val secondText = stringResource(id = R.string.notification)

        CustomShape()
        Image()
        CustomButton(secondText, 16.dp)
    }
}

@Composable
fun CustomShape(
    width: Dp = 260.dp,
    height: Dp = 112.dp,
    color: Color = Color.Red,
    topLeftRadius: Dp = 16.dp,
    bottomRightRadius: Dp = 32.dp
) {
    Canvas(
        modifier = Modifier
            .padding(vertical = 50.dp)
            .size(width = width, height = height)
            .clip(
                RoundedCornerShape(
                    topStart = topLeftRadius,
                    bottomEnd = bottomRightRadius
                )
            )
            .background(color)
    ) {}
}

@Composable
fun Image() {
    Image(
        painter = painterResource(id = R.drawable.image),
        contentDescription = "Local PNG image",
        modifier = Modifier
            .size(width = 260.dp, height = 242.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    Lesson9Theme {
        SecondScreen()
    }
}