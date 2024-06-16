package com.example.weathernow.data.repository

import com.example.weathernow.data.api.serializable.weather.WeatherSerializable
import com.example.weathernow.data.api.ApiResult
import com.example.weathernow.data.model.CityModel

interface MainRepository {
    suspend fun getCities(): ApiResult<List<CityModel>>
    suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<WeatherSerializable>
    fun getCurrentCity(): CityModel?
    fun setCurrentCity(cityModel: CityModel)
}