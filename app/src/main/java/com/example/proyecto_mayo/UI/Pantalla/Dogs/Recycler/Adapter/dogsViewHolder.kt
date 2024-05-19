package com.example.proyecto_mayo.UI.Pantalla.Dogs.Recycler.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.DTO.DataDogs
import com.example.proyecto_mayo.R
import com.example.proyecto_mayo.databinding.ItemDogGridLayoutBinding

class dogsViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDogGridLayoutBinding.bind(view)

    fun render(
        dogsListModel: DataDogs,
        onClickListener: (DataDogs) -> Unit,

        ){

        Glide.with(binding.ivDogGrid.context)
            .load(dogsListModel.url)
            .apply(RequestOptions().placeholder(R.drawable.loading))
            .into(binding.ivDogGrid)

        itemView.setOnClickListener {
            onClickListener(dogsListModel)
        }


    }
}