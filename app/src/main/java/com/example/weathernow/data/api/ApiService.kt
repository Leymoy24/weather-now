package com.example.weathernow.data.api

import com.example.weathernow.data.api.serializable.weather.WeatherSerializable
import com.example.weathernow.data.model.CityModel

interface ApiService {
    suspend fun getCities(): ApiResult<List<CityModel>>
    suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<WeatherSerializable>
}