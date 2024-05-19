package com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.usAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.proyecto_mayo.Data.DTO.DataUs

import com.example.proyecto_mayo.databinding.ItemUsLayoutBinding

class usViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    val binding = ItemUsLayoutBinding.bind(view)

    fun render(
        usListModel: DataUs,
        onClickListener: (DataUs) -> Unit,

        ){

        Glide.with(binding.ivUs.context).load(usListModel.photo).into(binding.ivUs)

        itemView.setOnClickListener {
            onClickListener(usListModel)
        }


    }
}