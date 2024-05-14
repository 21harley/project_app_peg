package com.example.proyecto_mayo.UI.Pantalla.Home.Presenter

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        GlobalScope.launch(Dispatchers.IO) {
            try {
                val service = DogApiClient.service

//                // Obtener todas las razas
//                val allBreedsCall = service.getAllBreeds()
//                val allBreedsResponse = allBreedsCall.execute().body()
//                Log.i("Hola", "Todas las razas: ${allBreedsResponse?.message?.keys}")
//
//                // Obtener una imagen aleatoria
//                val randomImageCall = service.getRandomImage()
//                val randomImageResponse = randomImageCall.execute().body()
//                Log.i("Hola", "Imagen aleatoria: ${randomImageResponse?.message}")
//
//                val imageUrl = randomImageResponse?.message
//
//                // Cambia al hilo principal para actualizar la UI
//                withContext(Dispatchers.Main) {
//                    Glide.with(this@MainActivity)
//                        .load(imageUrl)
//                        .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)) // Opcional: establece una imagen de placeholder mientras se carga la imagen
//                        .into(binding.includeCardDog.imageView)
//                }
                //dane great

//                // Obtener una imagen cantidad n aleatoria
//                val randomImageCall1 = service.getRandomImage(3)
//                val randomImageResponse1 = randomImageCall1.execute().body()
//                Log.i("Hola", "Imagen aleatoriaN: ${randomImageResponse1?.message}")

//                // Obtener una imagen de una raza
//                val randomImageCall2 = service.getRandomImageBreed("african")
//                val randomImageResponse2 = randomImageCall2.execute().body()
//                Log.i("Hola", "Imagen aleatoria: ${randomImageResponse2?.message}")

//                // Obtener n imagen de una raza
//                val randomImageCall3 = service.getRandomImageBreedSize("african",3)
//                val randomImageResponse3 = randomImageCall3.execute().body()
//                Log.i("Hola", "Imagen aleatoria: ${randomImageResponse3?.message}")

//                // Obtener lista de sub clase  de una raza
//                val randomImageCall4 = service.getRandomImageBreedSubList("dane")
//                val randomImageResponse4 = randomImageCall4.execute().body()
//                Log.i("Hola", "Imagen aleatoria: ${randomImageResponse4?.message}")

//                // Obtener imagenes de sub clase  de una raza
//                val randomImageCall5 = service.getRandomImageSubTypeImages("dane","great")
//                val randomImageResponse5 = randomImageCall5.execute().body()
//                Log.i("Hola", "Imagen aleatoria: ${randomImageResponse5?.message}")

//                // Obtener una imagen random de sub clase  de una raza
//                val randomImageCall6 = service.getRandomImageSubTypeImagesRandom("dane","great")
//                val randomImageResponse6 = randomImageCall6.execute().body()
//                Log.i("Hola", "Imagen aleatoria: ${randomImageResponse6?.message}")

//                // Obtener una cantidad n de una  sub clase  de una raza
//                val randomImageCall7 = service.getRandomImageSubTypeImagesSizeRadom("dane","great",3)
//                val randomImageResponse7 = randomImageCall7.execute().body()


            } catch (e: IOException) {
                // Manejar excepciones de red
            }
        }

    }
}