package com.example.proyecto_mayo.Data.Services.DogApi.DTO

sealed class StateDog {
    data class Success(val info: RandomImageResponse?) : StateDog()
    data class Error(val message: String) : StateDog()
    object Loading : StateDog()
}