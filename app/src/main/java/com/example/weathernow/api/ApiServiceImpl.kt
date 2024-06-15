package com.example.weathernow.api

import com.example.weathernow.api.serializable.CitySerializable
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

            when(response.status.value){
                in 200..299 -> {
                    ApiResult.Success(response.body<List<CitySerializable>>().map { it.convertToCityModel() })
                }

                else -> {
                    ApiResult.Error("Code ${response.status.value}: ${response.status.description}")
                }
            }
        } catch (e: Exception) {
            ApiResult.Error("${e.message}")
        }
    }

    override suspend fun getWeatherByCity(cityModel: CityModel): ApiResult<Float> {
        return try {
            val response = client.get(ApiRoutes.BASE_URL_WEATHER + ApiRoutes.WEATHER_v2_5) {
                url {
                    parameters.append("lat", cityModel.latitude)
                    parameters.append("lon", cityModel.longitude)
                    parameters.append("appid", "547de033f23db97460986b194b8b5a45")
                }
            }

            when(response.status.value){
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