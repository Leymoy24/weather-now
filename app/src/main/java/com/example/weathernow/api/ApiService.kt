package com.example.weathernow.api

import com.example.example.WeatherSerializable
import com.example.weathernow.data.model.CityModel

interface ApiService {
    suspend fun getCities(): ApiResult<List<CityModel>>
    suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<WeatherSerializable>
}