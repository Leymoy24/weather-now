package com.example.weathernow.data.api.serializable

import com.example.weathernow.data.model.CityModel
import kotlinx.serialization.Serializable

@Serializable
data class CitySerializable(
    val id: String,
    val city: String,
    val latitude: String,
    val longitude: String
) {
    fun convertToCityModel() = CityModel(id, if (city == "") "Безымянный" else city, latitude, longitude)
}
