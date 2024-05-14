package com.example.proyecto_mayo.UI.Pantalla.Home.Presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter.DataAdoptProvider
import com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter.adoptAdapter
import com.example.proyecto_mayo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var adoptList:MutableList<DataAdopt> =
        DataAdoptProvider.adoptList.toMutableList()

    private lateinit var adapterDataAdopt: adoptAdapter

    private  var adoptllmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val service = DogApiClient.service
//
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
////                // Cambia al hilo principal para actualizar la UI
////                withContext(Dispatchers.Main) {
////                    Glide.with(this@MainActivity)
////                        .load(imageUrl)
////                        .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)) // Opcional: establece una imagen de placeholder mientras se carga la imagen
////                        .into(binding.includeCardDog.imageView)
//                }
//            } catch (e: IOException) {
//                // Manejar excepciones de red
//            }
//        }

        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapterDataAdopt = adoptAdapter(
            adoptList = adoptList,
            onClickListener = { adopt -> adoptOnItemSelected(adopt)},
        )
        binding.adoptRecycler.layoutManager = adoptllmanager
        binding.adoptRecycler.adapter = adapterDataAdopt
    }
    private fun adoptOnItemSelected(adopt: DataAdopt) {
        var intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("img", adopt.photo)
        startActivity(intent)
        }
    }