package com.example.proyecto_mayo.UI.Pantalla.Dogs.Presenter

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataDogs
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter.DataDogsProvider
import com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter.dogsAdapter
import com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter.adoptAdapter
import com.example.proyecto_mayo.UI.Pantalla.Home.ViewModel.HomeViewModel
import com.example.proyecto_mayo.databinding.ActivityDogsBinding
import com.example.proyecto_mayo.databinding.ActivityMainBinding

class DogsActivity : AppCompatActivity() {

    private var dogsList:MutableList<DataDogs> =
        DataDogsProvider.dogsList.toMutableList()

    private lateinit var adapterDataDogs: dogsAdapter
    private   var  viewModelHome = HomeViewModel()
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

        initRecyclerView()
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
            it.putExtra("dogPhoto", dogs.photo)
            startActivity(it)
        }
    }
}