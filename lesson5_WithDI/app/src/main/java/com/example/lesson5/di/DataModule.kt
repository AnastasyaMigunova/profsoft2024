package com.example.lesson5.di

import com.example.lesson5.data.api.WeatherApi
import com.example.lesson5.data.repository.WeatherRepository
import com.example.lesson5.domain.repository.WeatherRepositoryImpl
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepositoryImpl {
        return WeatherRepository(weatherApi)
    }
}