package com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataDogs
import com.example.proyecto_mayo.R


class dogsAdapter(
    private var dogsList: List<DataDogs>,
    private val onClickListener: (DataDogs) -> Unit,
) :
RecyclerView.Adapter<dogsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return dogsViewHolder(layoutInflater.inflate(R.layout.item_dog_grid_layout, parent, false))

    }

    override fun onBindViewHolder(holder: dogsViewHolder, position: Int) {
        val item = dogsList[position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }


}