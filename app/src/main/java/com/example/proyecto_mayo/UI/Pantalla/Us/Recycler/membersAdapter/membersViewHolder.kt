package com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.membersAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataMembers

import com.example.proyecto_mayo.databinding.ItemMembersLayoutBinding

class membersViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    val binding = ItemMembersLayoutBinding.bind(view)

    fun render(
        membersListModel: DataMembers,
        onClickListener: (DataMembers) -> Unit,

        ){

        Glide.with(binding.ivMembers.context).load(membersListModel.photo).into(binding.ivMembers)

        itemView.setOnClickListener {
            onClickListener(membersListModel)
        }


        }
}