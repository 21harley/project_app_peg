package com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.usAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_mayo.Data.DTO.DataUs
import com.example.proyecto_mayo.R


class usAdapter(
    private var usList: List<DataUs>,
    // Lambda
    private val onClickListener: (DataUs) -> Unit
    ) :
    RecyclerView.Adapter<usViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return usViewHolder(layoutInflater.inflate(R.layout.item_us_layout, parent, false))

        }

        override fun getItemCount(): Int {
            return usList.size
        }

        override fun onBindViewHolder(holder: usViewHolder, position: Int) {
            val item = usList[position]
            holder.render(item,onClickListener)

        }
}