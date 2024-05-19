package com.example.proyecto_mayo.Data.Services.DogApi.DTO

sealed class StateBreedDog {
    data class Success(val info: RandomImageResponse?) : StateBreedDog()
    data class Error(val message: String) : StateBreedDog()
    object Loading : StateBreedDog()
}