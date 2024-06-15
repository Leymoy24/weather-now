package com.example.weathernow.data.repository

import com.example.example.WeatherSerializable
import com.example.weathernow.api.ApiResult
import com.example.weathernow.api.ApiService
import com.example.weathernow.data.model.CityModel
import com.example.weathernow.data.source.SessionStorage

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
): MainRepository {

    override suspend fun getCities(): ApiResult<List<CityModel>> {
        return apiService.getCities()
    }

    override suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<WeatherSerializable> {
        return apiService.getWeatherByCity(cityModel = cityModel)
    }

    override fun getCurrentCity(): CityModel? {
        return sessionStorage.currentCityModel
    }

    override fun setCurrentCity(cityModel: CityModel) {
        sessionStorage.currentCityModel = cityModel
    }

}