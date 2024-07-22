package com.example.lesson5.ui.main_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson5.data.repository.WeatherRepository
import com.example.lesson5.domain.interactor.WeatherInteractor
import com.example.lesson5.domain.interactor.WeatherInteractorImpl
import com.example.lesson5.ui.WeatherVo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel : ViewModel() {

    private val _weather = MutableLiveData<WeatherVo>()
    val weather: LiveData<WeatherVo> get() = _weather

    private val weatherInteractor: WeatherInteractorImpl =
        WeatherInteractor(weatherRepository = WeatherRepository())

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherData = withContext(Dispatchers.IO) {
                    weatherInteractor.getWeather()
                }
                _weather.postValue(weatherData)
            } catch (e: Exception) {
                Log.e("APISERVICE", "Error fetching data", e)
            }
        }
    }
}