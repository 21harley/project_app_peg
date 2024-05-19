package com.example.proyecto_mayo.UI.Pantalla.Random.Presenter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.DTO.DataDogs
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDog
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Random.ViewModel.RandomViewModel
import com.example.proyecto_mayo.databinding.ActivityRandomBinding

class RandomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRandomBinding
    private var  viewModelRadom = RandomViewModel()
    private var dog =DataDogs(url="")
    private var consulta:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        viewModelRadom.callDogApi()
        viewModelRadom.data.observe(this, Observer { it
            when (it) {
                is StateDog.Loading -> {
                    // Mostrar loading
                    binding.progressBar2.visibility = View.VISIBLE
                    binding.ImageView.visibility = View.INVISIBLE
                }
                is StateDog.Success -> {
                    binding.progressBar2.visibility = View.GONE
                    it.info?.message.let {
                        dog = DataDogs(url = it.toString())
                        Glide.with(binding.ImageView.context)
                            .load(it)
                            .apply(RequestOptions().placeholder(R.drawable.loading1))
                            .fitCenter()
                            .into(binding.ImageView)

                    }
                    binding.ImageView.visibility = View.VISIBLE
                }
                is StateDog.Error -> {
                    // Mostrar mensaje de error
                    binding.progressBar2.visibility = View.GONE
                    binding.ImageView.visibility = View.INVISIBLE
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.btReload.setOnClickListener {
            if(consulta==false){
                call()
                consulta=true
                binding.btReload.animate()
                    .rotationY(90F)
                    .setDuration(250) // Ajusta la duración según sea necesario
                    .withEndAction {
                        binding.btReload.animate()
                            .rotationY(0F)
                            .setDuration(250) // Ajusta la duración según sea necesario
                            .withEndAction {
                                consulta = false
                            }
                    }
            }else{
                Toast.makeText(this,"Cargando...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btReturn.setOnClickListener {
            finish()
        }

        binding.ImageView.setOnClickListener {
            if (dog.url.length>5){
                dogsOnItemSelected(dog)
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }

        }
    }
    fun call(){
        viewModelRadom.callDogApi()
    }

    private fun dogsOnItemSelected(dogs: DataDogs) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra("dogPhoto", dogs.url)
            startActivity(it)
        }
    }
}