package com.example.proyecto_mayo.UI.Pantalla.LookFor.Presenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.DTO.DataDogs
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateBreedDog
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDog
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.LookFor.ViewModel.LookForViewModel
import com.example.proyecto_mayo.UI.Pantalla.Random.ViewModel.RandomViewModel
import com.example.proyecto_mayo.databinding.ActivityLookForBinding

class LookForActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLookForBinding
    private var  viewModelLookFor = LookForViewModel()
    private var dog = DataDogs(url="")
    private var consulta:Boolean=false
    private var query:Boolean=false
    private var queryName:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLookForBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        binding.lookImage.setImageResource(R.drawable.perro)

        binding.searchBar.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                call(query)
                queryName = query
                Log.i("Hola",query + "Query")
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("Hola",newText+"new Texto")
                return false
            }
        })

        viewModelLookFor.data.observe(this, Observer {
            when (it) {
                is StateBreedDog.Loading -> {
                    // Mostrar loading
                    binding.progressBar4.visibility = View.VISIBLE
                    binding.lookImage.visibility = View.INVISIBLE
                }
                is StateBreedDog.Success -> {
                    binding.progressBar4.visibility = View.GONE

                    it.info?.message.let {
                        Log.i("Hola",it.toString())
                        dog = DataDogs(url = it.toString())
                        Glide.with(binding.lookImage.context)
                            .load(it)
                            .apply(RequestOptions().placeholder(R.drawable.loading))
                            .into(binding.lookImage)

                    }
                    binding.lookImage.visibility = View.VISIBLE
                    consulta = false
                }
                is StateBreedDog.Error -> {
                    // Mostrar mensaje de error
                    binding.progressBar4.visibility = View.GONE
                    binding.lookImage.visibility = View.INVISIBLE
                    binding.lookImage.setImageResource(R.drawable.perro)
                    Toast.makeText(this,"No se encontro ${queryName}", Toast.LENGTH_SHORT).show()
                    consulta = false
                }

                null -> {
                    binding.lookImage.setImageResource(R.drawable.perro)
                    Toast.makeText(this,"No se encontro ${queryName}", Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.lookImage.setOnClickListener {
            if (dog.url.length>5){
                dogsOnItemSelected(dog)
            }else{
                if(query!=false) Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btBack.setOnClickListener {
            finish()
        }
    }
    fun call(query:String){
        viewModelLookFor.callDogApi(query)
    }
    private fun dogsOnItemSelected(dogs: DataDogs) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra("dogPhoto", dogs.url)
            startActivity(it)
        }
    }
}