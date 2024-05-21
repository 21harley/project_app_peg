package com.example.proyecto_mayo.UI.Pantalla.Us.Presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_mayo.Data.DTO.DataMembers
import com.example.proyecto_mayo.Data.DTO.DataUs
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.membersAdapter.DataMembersProvider
import com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.membersAdapter.membersAdapter
import com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.usAdapter.DataUsProvider
import com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.usAdapter.usAdapter
import com.example.proyecto_mayo.databinding.ActivityMainBinding
import com.example.proyecto_mayo.databinding.ActivityUsBinding

class UsActivity : AppCompatActivity() {

    // recyclerView de integrantes
    // Lista inmutable transformada a una lista mutable que recibe el recyclerView desde un provider
    private var membersList:MutableList<DataMembers> =
        DataMembersProvider.membersList.toMutableList()
    // Transformar adapter a una variable
    private lateinit var adapterDataMembers: membersAdapter
    // Transformar layout manager a una variable
    private  var membersllmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    // recyclerView sobre nosotros
    // Lista inmutable transformada a una lista mutable que recibe el recyclerView desde un provider
    private var usList:MutableList<DataUs> =
        DataUsProvider.usList.toMutableList()
    // Transformar adapter a una variable
    private lateinit var adapterDataUs: usAdapter
    // Transformar layout manager a una variable
    private  var usllmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    private lateinit var binding: ActivityUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecycler()
        initComponent()
    }

    fun initRecycler(){
        initmembersRecyclerView()
        initUsRecyclerView()
    }
    fun initComponent(){
        // Boton de volver
        binding.btArrowBack.setOnClickListener{
           finish()
        }
    }

    // Integrantes
    private fun initmembersRecyclerView() {
        // Indicamos cual adapter debe utilizar el recyclerView
        adapterDataMembers = membersAdapter(
            // Lista que es recibida
            membersList = membersList,
            // Evento cuando se hace click en el item
            onClickListener = { members -> membersOnItemSelected(members)},
        )
        // Configuracion de bindeo
        binding.membersRecycler.layoutManager = membersllmanager
        binding.membersRecycler.adapter = adapterDataMembers
    }

    // Evento cuando se hace click
    private fun membersOnItemSelected(members: DataMembers) {
        Toast.makeText(this,members.name, Toast.LENGTH_SHORT).show()
    }

    // Sobre nosotros
    private fun initUsRecyclerView() {
        // Indicamos cual adapter debe utilizar el recyclerView
        adapterDataUs = usAdapter(
            // Lista que es recibida
            usList = usList,
            // Evento cuando se hace click en el item
            onClickListener = { us -> usOnItemSelected(us)},
        )
        // Configuracion de bindeo
        binding.usRecycler.layoutManager = usllmanager
        binding.usRecycler.adapter = adapterDataUs
    }

    // Evento cuando se hace click
    private fun usOnItemSelected(us: DataUs) {
        Toast.makeText(this,us.name, Toast.LENGTH_SHORT).show()
    }
}