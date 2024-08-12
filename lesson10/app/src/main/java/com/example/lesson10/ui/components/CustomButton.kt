package com.example.lesson10.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson10.ui.theme.Lesson10Theme
import com.example.lesson10.ui.theme.LocalCustomColors
import com.example.lesson10.ui.theme.LocalCustomTypography

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(
                color = LocalCustomColors.current.blue,
            )
            .padding(20.dp),
        text = text,
        color = Color.White,
        style = LocalCustomTypography.current.description,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun ButtonPreview() {
    Lesson10Theme {
        CustomButton(text = "Text") {}
    }
}