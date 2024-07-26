package com.example.lesson5.ui.main_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson5.R
import com.example.lesson5.databinding.FragmentMainBinding
import com.example.lesson5.ui.WeatherVo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.weather.observe(viewLifecycleOwner) { weatherVo ->
            updateInformation(weatherVo)
        }
    }

    private fun updateInformation(weather: WeatherVo) {
        binding.apply {
            textViewCityName.text = weather.city
            textViewTemp.text = formatTemperature(weather.temp)
            textViewFeelsLikeTemp.text =
                getString(R.string.feels_like, weather.tempFeelsLike.toInt())

            textViewWeatherDescription.text = buildString {
                append(" ")
                append(weather.weatherDescription)
                append(". ")
            }

            textViewMinTemp.text = getString(R.string.min_temp, weather.minTemp.toInt())
            textViewMaxTemp.text = getString(R.string.max_temp, weather.maxTemp.toInt())
            textViewPressure.text = getString(R.string.pressure, weather.pressure)
            textViewHumidity.text = getString(R.string.humidity, weather.humidity)
            textViewVisibility.text = getString(R.string.visibility, weather.visibility)
            textViewWindSpeed.text = getString(R.string.wind_speed, weather.windSpeed)
            textViewGust.text = getString(R.string.gust, weather.windGust)
            textViewDirection.text = getString(R.string.direction, weather.windDirection)
        }
    }

    private fun formatTemperature(temp: Double): String {
        return "${temp.toInt()}°C"
    }

}