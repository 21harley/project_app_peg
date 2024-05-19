package com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.membersAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.proyecto_mayo.Data.DTO.DataMembers
import com.example.proyecto_mayo.R


class membersAdapter (
    private var membersList: List<DataMembers>,
    private val onClickListener: (DataMembers) -> Unit
    ) :
    RecyclerView.Adapter<membersViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): membersViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return membersViewHolder(
                layoutInflater.inflate(
                    R.layout.item_members_layout,
                    parent,
                    false
                )
            )

        }

        override fun getItemCount(): Int {
            return membersList.size
        }

        override fun onBindViewHolder(holder: membersViewHolder, position: Int) {
            val item = membersList[position]
            holder.render(item, onClickListener)

        }
    }