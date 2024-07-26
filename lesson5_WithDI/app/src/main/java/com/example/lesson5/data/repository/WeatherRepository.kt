package com.example.lesson5.data.repository

import com.example.lesson5.data.api.WeatherApi
import com.example.lesson5.domain.models.Weather
import com.example.lesson5.domain.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.round

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRepositoryImpl {
    override suspend fun getWeather(): Weather {
        return withContext(Dispatchers.IO) {
            try {
                val response = weatherApi.getWeather()
                Weather(
                    city = response.name,
                    temp = changeTemp(response.main.temp),
                    tempFeelsLike = changeTemp(response.main.feelsLike),
                    weatherDescription = response.weather[0].description,
                    minTemp = changeTemp(response.main.tempMin),
                    maxTemp = changeTemp(response.main.tempMax),
                    pressure = response.main.pressure,
                    humidity = response.main.humidity,
                    visibility = changeVisibility(response.visibility),
                    windSpeed = response.wind.speed,
                    windGust = response.wind.gust,
                    windDirection = degreeToWindDirection(response.wind.deg)
                )
            } catch (e: Exception) {
                throw Exception("Failed to fetch weather data: ${e.message}")
            }
        }
    }

    private fun changeVisibility(visibility: Int): Double {
        return visibility / 1000.0
    }

    private fun degreeToWindDirection(degree: Int): String {
        val directions = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val index = ((degree + 22.5) / 45).toInt() % 8
        return directions[index]
    }

    private fun changeTemp(temp: Double): Double {
        return round(temp - 273)
    }
}