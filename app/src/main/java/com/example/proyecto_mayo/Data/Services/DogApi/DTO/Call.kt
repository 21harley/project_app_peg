package com.example.proyecto_mayo.Data.Services.DogApi.DTO

data class DogBreedsResponse(
    val message: Map<String, List<String>>?,
    val status: String,
    val error: DogApiError? = null
)

data class RandomImageResponse(
    val message: String?,
    val status: String,
    val error: DogApiError? = null
)

data class RandomImagesResponse(
    val message: List<String>?,
    val status: String,
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