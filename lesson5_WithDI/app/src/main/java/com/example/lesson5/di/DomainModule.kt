package com.example.lesson5.di

import com.example.lesson5.data.repository.WeatherRepository
import com.example.lesson5.domain.interactor.WeatherInteractor
import com.example.lesson5.domain.interactor.WeatherInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideWeatherInteractor(weatherRepository: WeatherRepository): WeatherInteractorImpl {
        return WeatherInteractor(weatherRepository)
    }
}