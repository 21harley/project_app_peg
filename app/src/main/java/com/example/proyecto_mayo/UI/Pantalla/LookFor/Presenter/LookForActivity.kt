package com.example.proyecto_mayo.UI.Pantalla.LookFor.Presenter

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.DTO.DataDogs
import com.example.proyecto_mayo.Data.Respository.ConnectivityApp
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateBreedDog
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDogAll
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.LookFor.ViewModel.LookForViewModel
import com.example.proyecto_mayo.databinding.ActivityLookForBinding

class LookForActivity : AppCompatActivity(), ConnectivityApp.ConnectivityReceiverListener  {
    private lateinit var binding: ActivityLookForBinding
    private lateinit var connectivityApp: ConnectivityApp
    private var connection :Boolean=true
    private var  viewModelLookFor = LookForViewModel()
    private var dog = DataDogs(url="")
    private var consulta:Boolean=false
    private var query:Boolean=false
    private var queryName:String=""
    private var listNameDevice = listOf<String>()
    private var contSelect = 0
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
        binding.containerError.visibility=View.GONE

        // Inicializar y registrar el receptor
        connectivityApp = ConnectivityApp(this)
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityApp, filter)

        binding.searchBar.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                if (connection){
                    call(query)
                }else{
                    error_makeText()
                }
                queryName = query
                Log.i("Hola",query + "Query")
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("Hola",newText+"new Texto")
                return false
            }
        })
        //consulta de una foto por raza
        viewModelLookFor.data.observe(this, Observer {
            when (it) {
                is StateBreedDog.Loading -> {
                    // Mostrar loading
                    binding.containerError.visibility = View.GONE
                    binding.progressBar4.visibility = View.VISIBLE
                    binding.dogSeachView.visibility = View.INVISIBLE
                }
                is StateBreedDog.Success -> {
                    binding.progressBar4.visibility = View.GONE

                    it.info?.message.let {
                        Log.i("Hola",it.toString())
                        dog = DataDogs(url = it.toString())
                        Glide.with(binding.lookImage.context)
                            .load(it)
                            .apply(RequestOptions().placeholder(R.drawable.loading1))
                            .fitCenter()
                            .into(binding.lookImage)

                    }
                    binding.dogSeachView.visibility = View.VISIBLE
                    consulta = false
                }
                is StateBreedDog.Error -> {
                    // Mostrar mensaje de error
                    binding.progressBar4.visibility = View.GONE
                    binding.dogSeachView.visibility = View.GONE
                    callAll()
                    binding.containerError.visibility = View.VISIBLE
                    Toast.makeText(this,"No se encontro1 ${queryName}", Toast.LENGTH_SHORT).show()
                    consulta = false
                }

                null -> {
                    binding.lookImage.setImageResource(R.drawable.perro)
                    Toast.makeText(this,"No se encontro2 ${queryName}", Toast.LENGTH_SHORT).show()
                }
            }

        })

        //consulta para mostrar todas las razas
        viewModelLookFor.dataAll.observe(this, Observer {
            when (it) {
                is StateDogAll.Loading -> {

                }
                is StateDogAll.Success -> {
                    Log.i("HOLA",it.info?.message?.keys.toString())
                    var lista = it.info?.message?.keys?.toList()
                    if (lista!=null){
                        binding.errorSearchSpinner.adapter = ArrayAdapter<String>(
                            this,android.R.layout.simple_list_item_1,lista
                        )
                        listNameDevice = lista
                        contSelect = 0
                    }
                }
                is StateDogAll.Error -> {}
                null -> {}
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
        // spinner select
        binding.errorSearchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Toast.makeText(context!!, "onNothingSelected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var textAux = listNameDevice.get(binding.errorSearchSpinner.firstVisiblePosition)
                Log.i("HOLA","Select ${textAux}")
                contSelect++
                if (contSelect>1 && connection==true){
                    binding.searchBar.setQuery(textAux, false)
                    call(textAux)
                }
            }
        }
    }
    fun error_makeText(){
        Toast.makeText(this,"Error de conexion", Toast.LENGTH_SHORT).show()
    }
    fun call(query:String){
        viewModelLookFor.callDogApi(query)
    }
    fun callAll(){
        viewModelLookFor.callDogApiAll()
    }
    private fun dogsOnItemSelected(dogs: DataDogs) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra("dogPhoto", dogs.url)
            startActivity(it)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if(isConnected){
            connection=true
        }else{
            connection=false
            Toast.makeText(this,"Error de conexion", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Desregistrar el receptor para evitar fugas de memoria
        unregisterReceiver(connectivityApp)
    }
}