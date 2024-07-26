package com.example.lesson5.domain.repository

import com.example.lesson5.domain.models.Weather

interface WeatherRepositoryImpl {
    suspend fun getWeather() : Weather
}