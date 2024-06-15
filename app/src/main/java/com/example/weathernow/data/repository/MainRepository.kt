package com.example.weathernow.data.repository

import com.example.example.WeatherSerializable
import com.example.weathernow.api.ApiResult
import com.example.weathernow.data.model.CityModel

interface MainRepository {
    suspend fun getCities(): ApiResult<List<CityModel>>
    suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<WeatherSerializable>
    fun getCurrentCity(): CityModel?
    fun setCurrentCity(cityModel: CityModel)
}