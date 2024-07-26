package com.example.lesson5.data.api

import com.example.lesson5.data.models.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query(QUERY_PARAM_CITY) cityName: String = "saratov",
        @Query(QUERY_PARAM_API_ID) key: String = API_KEY
    ): WeatherDTO

    companion object {
        const val QUERY_PARAM_CITY = "q"
        const val QUERY_PARAM_API_ID = "appid"
        const val API_KEY = "60a84e853af11620a1b5050d9de81b3d"
    }
}