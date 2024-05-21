package com.example.proyecto_mayo.Data.Services.DogApi.DTO

sealed class StateDogAll {
    data class Success(val info: DogBreedsResponse?) : StateDogAll()
    data class Error(val message: String) : StateDogAll()
    object Loading : StateDogAll()
}