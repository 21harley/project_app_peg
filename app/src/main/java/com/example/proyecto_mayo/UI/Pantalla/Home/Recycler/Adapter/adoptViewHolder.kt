package com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt

import com.example.proyecto_mayo.databinding.ItemAdoptRecyclerBinding


class adoptViewHolder(val view : View) : ViewHolder(view) {

    val binding = ItemAdoptRecyclerBinding.bind(view)

    fun render(
        adoptListModel: DataAdopt,
        onClickListener: (DataAdopt) -> Unit,

    ){

        Glide.with(binding.ivAdopt.context).load(adoptListModel.photo).into(binding.ivAdopt)

        itemView.setOnClickListener {
            onClickListener(adoptListModel)
        }


    }
}