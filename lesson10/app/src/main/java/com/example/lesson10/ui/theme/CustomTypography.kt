package com.example.lesson10.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class CustomTypography(
    val title: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    val description: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),
    val small: TextStyle =TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp
    )
)

val LocalCustomTypography = staticCompositionLocalOf { CustomTypography() }