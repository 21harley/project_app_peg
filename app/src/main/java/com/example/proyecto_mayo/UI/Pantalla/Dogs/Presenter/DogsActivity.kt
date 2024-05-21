package com.example.proyecto_mayo.UI.Pantalla.Dogs.Presenter

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyecto_mayo.Data.DTO.DataDogs
import com.example.proyecto_mayo.Data.Respository.ConnectivityApp
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDogs
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter.dogsAdapter
import com.example.proyecto_mayo.UI.Pantalla.Dogs.ViewModel.DogsViewModel
import com.example.proyecto_mayo.databinding.ActivityDogsBinding

class DogsActivity : AppCompatActivity(), ConnectivityApp.ConnectivityReceiverListener  {


    private lateinit var connectivityApp: ConnectivityApp
    private var statusNewCall = false
    private var connection :Boolean=true
    private   var  viewModelHome = DogsViewModel()

    // Lista que recibe recyclerView
    private var dogsList:MutableList<DataDogs> = mutableListOf()
    // Transformar adapter a una variable
    private lateinit var adapterDataDogs: dogsAdapter
    // Transformar layout manager a una variable
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
        // Hacer invisible el contenedor de error
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        initRecyclerView()
        binding.containerError.visibility = View.GONE

        // Inicializar y registrar el receptor
        connectivityApp = ConnectivityApp(this)
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityApp, filter)

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

        //Boton de volver
        binding.btReturn.setOnClickListener{
            finish()
        }
        //Boton de refresh
        binding.btReload.setOnClickListener {
            if(statusNewCall==false && connection==true){
                call()
                statusNewCall=true
                binding.btReload.animate()
                    .rotationY(90F)
                    .setDuration(250) // Ajusta la duración según sea necesario
                    .withEndAction {
                        binding.btReload.animate()
                            .rotationY(0F)
                            .setDuration(250) // Ajusta la duración según sea necesario
                            .withEndAction {
                                statusNewCall = false
                            }
                    }
            }else{
                if (connection) Toast.makeText(this,"Cargando...", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun call(){
        viewModelHome.callDogApi()
    }
    private fun initRecyclerView() {
        // Indicamos cual adapter debe utilizar el recyclerView
        adapterDataDogs = dogsAdapter(
            // Lista que es recibida
            dogsList = dogsList,
            // Evento cuando se hace click en el item
            onClickListener = { dogs -> dogsOnItemSelected(dogs)}
        )
        // Configuracion de bindeo
        binding.recyclerDogs.layoutManager = dogsllmanager
        binding.recyclerDogs.adapter = adapterDataDogs
    }
    // Evento cuando se hace click
    private fun dogsOnItemSelected(dogs: DataDogs) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra("dogPhoto", dogs.url)
            startActivity(it)
        }
    }

    // Control de evento basado en la conectividad
    override fun onNetworkConnectionChanged(isConnected: Boolean) {

        // Mostrar recyclerView
        if(isConnected){
            connection=true
            call()
            binding.containerError.visibility  = View.GONE
            binding.progressBar3.visibility = View.VISIBLE
            binding.recyclerDogs.visibility = View.VISIBLE

        // Mostrar contenedor de error
        }else{
            connection=false
            binding.containerError.visibility = View.VISIBLE
            binding.progressBar3.visibility = View.GONE
            binding.recyclerDogs.visibility = View.GONE
            Toast.makeText(this,"Error de conexion", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Desregistrar el receptor para evitar fugas de memoria
        unregisterReceiver(connectivityApp)
    }
}