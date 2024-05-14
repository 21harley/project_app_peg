package com.example.proyecto_mayo.UI.Pantalla.Home.Recycler.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt
import com.example.proyecto_mayo.R

import com.example.proyecto_mayo.databinding.ItemAdoptRecyclerBinding


class adoptViewHolder(val view : View) : ViewHolder(view) {

    val binding = ItemAdoptRecyclerBinding.bind(view)

    fun render(
        adoptListModel: DataAdopt,
        onClickListener: (DataAdopt) -> Unit,

    ){
//        Glide.with(this@MainActivity)
//                       .load(imageUrl)
//                        .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)) // Opcional: establece una imagen de placeholder mientras se carga la imagen
//                        .into(binding.includeCardDog.imageView)
//        Glide.with(binding.ivAdopt.context).load(
//            DataAdopt
//        ).into(binding.ivAdopt)
          Glide.with(binding.ivAdopt.context)
              .load(adoptListModel.url)
              .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
              .into(binding.ivAdopt)

        itemView.setOnClickListener {
            onClickListener(adoptListModel)
        }


    }
}