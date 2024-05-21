package com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.usAdapter

import com.example.proyecto_mayo.Data.DTO.DataUs
import com.example.proyecto_mayo.R

class DataUsProvider {
    companion object{

        var usList = listOf<DataUs>(

            DataUs(R.drawable.jhonalgo,"Jhon"),
            DataUs(R.drawable.marianoalgo,"Mariano"),
            DataUs(R.drawable.eliseoalgo,"Eliseo"),
            )
    }
}