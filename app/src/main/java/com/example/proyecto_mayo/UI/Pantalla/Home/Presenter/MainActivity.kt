package com.example.proyecto_mayo.UI.Pantalla.Home.Presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter.DataAdoptProvider
import com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter.adoptAdapter
import com.example.proyecto_mayo.UI.Pantalla.Home.ViewModel.HomeViewModel
import com.example.proyecto_mayo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var adoptList:MutableList<DataAdopt> = mutableListOf()

    private lateinit var adapterDataAdopt: adoptAdapter
    private   var  viewModelHome = HomeViewModel()
    private  var adoptllmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       viewModelHome.data.observe(this, Observer { breeds ->
           breeds?.message?.forEach {
               adoptList.add(DataAdopt(url = it))
           }
           initRecyclerView()
        }
       )

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
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra("dogPhoto", adopt.url)
            startActivity(it)
        }
    }
}