package com.example.proyecto_mayo.Data.Services.DogApi.Class
import com.example.proyecto_mayo.Data.Services.DogApi.DogApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogApiClient {
    private const val BASE_URL = "https://dog.ceo/api/"

    // Crear instancia de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Crear instancia del servicio
    val service: DogApiService = retrofit.create(DogApiService::class.java)
}

//                // Crear instancia de Retrofit
//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://dog.ceo/api/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//
//                // Crear instancia del servicio
//                val service = retrofit.create(DogApiService::class.java)
//
//                // Realizar la solicitud
//                val response = service.getAllBreeds().execute()
//
//                // Procesar la respuesta en el hilo principal
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        val data = response.body()
//                        // Hacer algo con los datos
//                    } else {
//                        // Manejar errores de la solicitud
//                    }
//                }