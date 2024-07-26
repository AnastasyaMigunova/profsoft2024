package com.example.lesson5.data.models

data class WeatherDTO(
    val main: Main,
    val name: String,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)