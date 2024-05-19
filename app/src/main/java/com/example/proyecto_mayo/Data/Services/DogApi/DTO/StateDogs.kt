package com.example.proyecto_mayo.Data.Services.DogApi.DTO
sealed class StateDogs {
    data class Success(val info: RandomImagesResponse?) : StateDogs()
    data class Error(val message: String) : StateDogs()
    object Loading : StateDogs()
}