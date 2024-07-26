package com.example.lesson5.domain.interactor

import com.example.lesson5.domain.repository.WeatherRepositoryImpl
import com.example.lesson5.ui.WeatherVo
import javax.inject.Inject

class WeatherInteractor @Inject constructor(private val weatherRepository: WeatherRepositoryImpl) :
    WeatherInteractorImpl {
    override suspend fun getWeather(): WeatherVo {
        val response = weatherRepository.getWeather()
        return WeatherVo(
            city = response.city,
            temp = response.temp,
            tempFeelsLike = response.tempFeelsLike,
            weatherDescription = response.weatherDescription,
            minTemp = response.minTemp,
            maxTemp = response.maxTemp,
            pressure = response.pressure,
            humidity = response.humidity,
            visibility = response.visibility,
            windSpeed = response.windSpeed,
            windGust = response.windGust,
            windDirection = response.windDirection
        )
    }
}