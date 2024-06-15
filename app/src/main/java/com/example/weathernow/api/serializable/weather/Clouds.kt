package com.example.example

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    @SerializedName("all") var all: Int? = null
)