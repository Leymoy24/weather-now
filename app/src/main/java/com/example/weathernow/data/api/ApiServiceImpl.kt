package com.example.weathernow.data.api

import com.example.weathernow.data.api.serializable.weather.WeatherSerializable
import com.example.weathernow.BuildConfig
import com.example.weathernow.data.api.serializable.CitySerializable
import com.example.weathernow.data.model.CityModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getCities(): ApiResult<List<CityModel>> {
        return try {
            val response = client.get(ApiRoutes.BASE_URL_CITIES)

            when (response.status.value) {
                in 200..299 -> {
                    ApiResult.Success(
                        response.body<List<CitySerializable>>().map { it.convertToCityModel() })
                }

                else -> {
                    ApiResult.Error("Code ${response.status.value}: ${response.status.description}")
                }
            }
        } catch (e: Exception) {
            ApiResult.Error("${e.message}")
        }
    }

    override suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<WeatherSerializable> {
        return try {
            val response = client.get(ApiRoutes.BASE_URL_WEATHER + ApiRoutes.WEATHER_v2_5) {
                url {
                    parameters.append("lat", cityModel.latitude)
                    parameters.append("lon", cityModel.longitude)
                    parameters.append("appid", BuildConfig.API_KEY_OPEN_WEATHER)
                    parameters.append("units", "metric")
                }
            }

            when (response.status.value) {
                in 200..299 -> {
                    ApiResult.Success(response.body())
                }

                else -> {
                    ApiResult.Error("Code ${response.status.value}: ${response.status.description}")
                }
            }
        } catch (e: Exception) {
            ApiResult.Error("${e.message}")
        }
    }
}