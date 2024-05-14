package com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt

class adoptAdapter(
    private var adoptList: List<DataAdopt>,
    private val onClickListener: (DataAdopt) -> Unit,
    ) :
    RecyclerView.Adapter<adoptViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adoptViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return adoptViewHolder(layoutInflater.inflate(R.layout.item_adopt_recycler, parent, false))

    }

    override fun getItemCount(): Int {
        return adoptList.size
    }

    override fun onBindViewHolder(holder: adoptViewHolder, position: Int) {
        val item = adoptList[position]
        holder.render(item,onClickListener)

    }
}