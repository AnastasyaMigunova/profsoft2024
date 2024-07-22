package com.example.lesson5.domain.models

data class Weather(
    val city: String,
    val temp: Double,
    val tempFeelsLike: Double,
    val weatherDescription: String,
    val minTemp: Double,
    val maxTemp: Double,
    val pressure: Int,
    val humidity: Int,
    val visibility: Double,
    val windSpeed: Double,
    val windGust: Double,
    val windDirection: String
)
