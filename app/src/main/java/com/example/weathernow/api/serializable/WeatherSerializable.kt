package com.example.weathernow.api.serializable

import kotlinx.serialization.Serializable

@Serializable
data class WeatherSerializable(
    val temp: Float
)
