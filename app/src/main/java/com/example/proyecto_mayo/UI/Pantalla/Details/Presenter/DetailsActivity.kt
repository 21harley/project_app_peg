package com.example.proyecto_mayo.UI.Pantalla.Details.Presenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.databinding.ActivityDetailsBinding
import com.example.proyecto_mayo.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding.detailText.text =
            "Cualquier dieta debe ser apropiada para el perro.a;os. Debe haber agua limpia y fresca disponible en todo momento"
        val data = intent.getStringExtra("dogPhoto")
//        Glide.with(binding.ivDog.context).load(data).into(binding.ivDog)
        Log.i("Hola",data.toString())
        Glide.with(binding.ivDog.context)
            .load(data)
            .apply(RequestOptions().placeholder(R.drawable.loading1))
            .fitCenter()
            .into(binding.ivDog)

        binding.btback.setOnClickListener{
            finish()
        }



    }
}


