package com.example.proyecto_mayo.Data.Services.DogApi.DTO

import com.google.gson.annotations.SerializedName

data class DogBreedsResponse(
    @SerializedName("message")
    val message: Map<String, List<String>>?,
    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val error: DogApiError? = null
)

data class RandomImageResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val error: DogApiError? = null
)

data class RandomImagesResponse(
    @SerializedName("message")
    val message: List<String>?,
    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val error: DogApiError? = null
)

data class DogBreedResponse(
    val message: List<String>?,
    val status: String,
    val error: DogApiError? = null
)

data class DogApiError(
    val code: Int,
    val message: String
)