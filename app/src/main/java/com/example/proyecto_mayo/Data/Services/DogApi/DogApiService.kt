package com.example.proyecto_mayo.Data.Services.DogApi
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DogBreedResponse
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DogBreedsResponse
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.RandomImageResponse
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.RandomImagesResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    //breeds
    @GET("breeds/list/all")
    fun getAllBreeds(): Call<DogBreedsResponse>

    @GET("breeds/image/random")
    fun getRandomImage(): Call<RandomImageResponse>

    @GET("breeds/image/random/{numRandom}")
    fun getRandomImage(@Path("numRandom") numRandom: Int): Call<RandomImagesResponse>

    //breed
    @GET("breed/{razaPerro}/images")
    fun getRandomImageBreed(@Path("razaPerro") razaPerro: String): Call<DogBreedResponse>
}

/*
Consultas HTTPS URL raiz : https://dog.ceo/api

breeds/


breed/

GET /{raza_perro}/images/random
{
    "message": "https://images.dog.ceo/breeds/affenpinscher/n02110627_11798.jpg",
    "status": "success"
}

GET /{raza_perro}/images/random/{numero_imagenes}
{
    "message": [numero_url_imagenes_raza_perro],
    "status": "success"
}

GET /{raza_perro}/{sub_tipo}/list
{
    "message": [
    "afghan",
    "basset",
    "blood",
    "english",
    "ibizan",
    "plott",
    "walker"
    ],
    "status": "success"
}

GET /{raza_perro}/{sub_tipo}/images
{
    "message": [
    "https://images.dog.ceo/breeds/hound-afghan/n02088094_3856.jpg",
    "https://images.dog.ceo/breeds/hound-afghan/n02088094_732.jpg",
    "https://images.dog.ceo/breeds/hound-afghan/n02088094_899.jpg"
    ],
    "status": "success"
}


GET /{raza_perro}/{sub_tipo}/images/random
{
    "message": "https://images.dog.ceo/breeds/hound-afghan/n02088094_3856.jpg",
    "status": "success"
}

GET /{raza_perro}/{sub_tipo}/images/random/{numero_radom}
{
    "message": [numero_radom_URL_images],
    "status": "success"
}

ERROR
{
    "status":"error",
    "message":"No route found for \"GET http:\/\/dog.ceo\/api\/breeds\/list\/allc\" with code: 0",
    "code":404
}
*/