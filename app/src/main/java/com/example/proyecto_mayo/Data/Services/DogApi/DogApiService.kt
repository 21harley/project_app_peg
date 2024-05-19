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
    fun getRandomImage(
        @Path("numRandom") numRandom: Int
    ): Call<RandomImagesResponse>

    //breed
    @GET("breed/{razaPerro}/images")
    fun getRandomImageBreed(
        @Path("razaPerro") razaPerro: String
    ): Call<DogBreedResponse>

    @GET("breed/{razaPerro}/images/random")
    fun getRandomImageBreedRandom(
        @Path("razaPerro") razaPerro: String
    ): Call<RandomImageResponse>

    @GET("breed/{razaPerro}/images/random/{numeroImagenes}")
    fun getRandomImageBreedSize(
        @Path("razaPerro") razaPerro: String,
        @Path("numeroImagenes") numRandom: Int)
    : Call<DogBreedResponse>

    @GET("breed/{razaPerro}/list")
    fun getRandomImageBreedSubList(
        @Path("razaPerro") razaPerro: String
    ): Call<DogBreedResponse>

    @GET("breed/{razaPerro}/{subTipo}/images")
    fun getRandomImageSubTypeImages(
        @Path("razaPerro") razaPerro: String,
        @Path("subTipo") subTipo: String
    ): Call<DogBreedResponse>

    @GET("breed/{razaPerro}/{subTipo}/images/random")
    fun getRandomImageSubTypeImagesRandom(
        @Path("razaPerro") razaPerro: String,
        @Path("subTipo") subTipo: String
    ): Call<RandomImageResponse>

    @GET("breed/{razaPerro}/{subTipo}/ima/random/{numeroImagenes}")
    fun getRandomImageSubTypeImagesSizeRadom(
        @Path("razaPerro") razaPerro: String,
        @Path("subTipo") subTipo: String,
        @Path("numeroImagenes") numRandom: Int
    ): Call<DogBreedResponse>

}

/*
Consultas HTTPS URL raiz : https://dog.ceo/api
ERROR
{
    "status":"error",
    "message":"No route found for \"GET http:\/\/dog.ceo\/api\/breeds\/list\/allc\" with code: 0",
    "code":404
}
*/