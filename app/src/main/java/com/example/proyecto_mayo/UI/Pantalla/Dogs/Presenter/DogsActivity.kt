package com.example.proyecto_mayo.UI.Pantalla.Dogs.Presenter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyecto_mayo.Data.DTO.DataAdopt
import com.example.proyecto_mayo.Data.DTO.DataDogs
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDogs
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter.DataDogsProvider
import com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter.dogsAdapter
import com.example.proyecto_mayo.UI.Pantalla.Dogs.ViewModel.DogsViewModel
import com.example.proyecto_mayo.UI.Pantalla.Home.ViewModel.HomeViewModel
import com.example.proyecto_mayo.databinding.ActivityDogsBinding

class DogsActivity : AppCompatActivity() {

    private var dogsList:MutableList<DataDogs> = mutableListOf()
    private var statusNewCall = false
    private lateinit var adapterDataDogs: dogsAdapter
    private   var  viewModelHome = DogsViewModel()
    private  var dogsllmanager = GridLayoutManager(this, 3)

    private lateinit var binding: ActivityDogsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        initRecyclerView()


        viewModelHome.data.observe(this, Observer { state ->
            when (state) {
                is StateDogs.Loading -> {
                    // Mostrar loading
                    binding.progressBar3.visibility = View.VISIBLE
                    binding.recyclerDogs.visibility = View.INVISIBLE
                }
                is StateDogs.Success -> {
                    binding.progressBar3.visibility = View.GONE
                    // Mostrar datos de éxito
                    dogsList = mutableListOf()
                    state?.info?.message?.forEach {
                        dogsList.add(DataDogs(url = it))
                    }
                    initRecyclerView()
                    binding.recyclerDogs.visibility = View.VISIBLE
                }
                is StateDogs.Error -> {
                    // Mostrar mensaje de error
                    binding.progressBar3.visibility = View.GONE
                    binding.recyclerDogs.visibility = View.VISIBLE
                    Toast.makeText(this,state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })


        binding.btReturn.setOnClickListener{
            finish()
        }

        binding.btReload.setOnClickListener {
            if(statusNewCall==false){
                call()
                statusNewCall=true
                Thread {
                    try {
                        Thread.sleep(3000) // Esperar 3 segundos
                        statusNewCall = false

                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }.start()
            }else{
                Toast.makeText(this,"Cargando...", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun call(){
        viewModelHome.callDogApi()
    }
    private fun initRecyclerView() {
        adapterDataDogs = dogsAdapter(
            dogsList = dogsList,
            onClickListener = { dogs -> dogsOnItemSelected(dogs)}
        )
        binding.recyclerDogs.layoutManager = dogsllmanager
        binding.recyclerDogs.adapter = adapterDataDogs
    }
    private fun dogsOnItemSelected(dogs: DataDogs) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra("dogPhoto", dogs.url)
            startActivity(it)
        }
    }
}