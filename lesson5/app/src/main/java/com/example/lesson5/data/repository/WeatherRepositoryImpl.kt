package com.example.lesson5.data.repository

import com.example.lesson5.domain.models.Weather

interface WeatherRepositoryImpl {
    suspend fun getWeather() : Weather
}