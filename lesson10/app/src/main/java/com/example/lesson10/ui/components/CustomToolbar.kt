package com.example.lesson10.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson10.R
import com.example.lesson10.ui.theme.Lesson10Theme
import com.example.lesson10.ui.theme.LocalCustomTypography

@Composable
fun CustomToolbar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    backgroundColor: Color = Color.White,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .heightIn(min = 56.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        onBackClick?.let {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clickable(onClick = onBackClick),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            text = title,
            style = LocalCustomTypography.current.title
        )
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    Lesson10Theme {
        CustomToolbar(
            title = "Title",
            onBackClick = {}
        )
    }
}