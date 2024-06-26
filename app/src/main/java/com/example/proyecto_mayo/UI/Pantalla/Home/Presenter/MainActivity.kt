package com.example.proyecto_mayo.UI.Pantalla.Home.Presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt
import com.example.proyecto_mayo.UI.Pantalla.Details.Presenter.DetailsActivity
import com.example.proyecto_mayo.UI.Pantalla.Dogs.Presenter.DogsActivity
import com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter.adoptAdapter
import com.example.proyecto_mayo.UI.Pantalla.Home.ViewModel.HomeViewModel
import com.example.proyecto_mayo.UI.Pantalla.LookFor.Presenter.LookForActivity
import com.example.proyecto_mayo.UI.Pantalla.Random.Presenter.RandomActivity
import com.example.proyecto_mayo.UI.Pantalla.Us.Presenter.UsActivity
import com.example.proyecto_mayo.databinding.ActivityMainBinding

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
        initComponents()




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
            onClickListener = { adopt -> adoptOnItemSelected(adopt)}
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


  private fun initComponents(){
      binding.btRandom.setOnClickListener {
          Intent(this, RandomActivity::class.java).also {
              startActivity(it)
          }
      }

      binding.btDogs.setOnClickListener {
          Intent(this, DogsActivity::class.java).also {
              startActivity(it)
          }
      }
      binding.btUs.setOnClickListener {
          Intent(this, UsActivity::class.java).also {
              startActivity(it)
          }
      }
      binding.btSearch.setOnClickListener {
          Intent(this, LookForActivity::class.java).also {
              startActivity(it)
          }
      }
  }
}

