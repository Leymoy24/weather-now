package com.example.example

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerializedName("1h") var h: Double? = null
)