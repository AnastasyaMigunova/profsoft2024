package com.example.lesson5.domain.interactor

import com.example.lesson5.ui.WeatherVo

interface WeatherInteractorImpl {
    suspend fun getWeather() : WeatherVo
}