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

    // Members Recycler
    private var membersList:MutableList<DataMembers> =
        DataMembersProvider.membersList.toMutableList()

    private lateinit var adapterDataMembers: membersAdapter

    private  var membersllmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    // Us Recycler
    private var usList:MutableList<DataUs> =
        DataUsProvider.usList.toMutableList()

    private lateinit var adapterDataUs: usAdapter

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

        binding.btArrowBack.setOnClickListener{
           finish()
        }
    }

    //members
    private fun initmembersRecyclerView() {
        adapterDataMembers = membersAdapter(
            membersList = membersList,
            onClickListener = { members -> membersOnItemSelected(members)},
        )
        binding.membersRecycler.layoutManager = membersllmanager
        binding.membersRecycler.adapter = adapterDataMembers
    }
    private fun membersOnItemSelected(members: DataMembers) {
        Toast.makeText(this,members.name, Toast.LENGTH_SHORT).show()
    }

    // Us
    private fun initUsRecyclerView() {
        adapterDataUs = usAdapter(
            usList = usList,
            onClickListener = { us -> usOnItemSelected(us)},
        )
        binding.usRecycler.layoutManager = usllmanager
        binding.usRecycler.adapter = adapterDataUs
    }
    private fun usOnItemSelected(us: DataUs) {
        Toast.makeText(this,us.name, Toast.LENGTH_SHORT).show()
    }
}