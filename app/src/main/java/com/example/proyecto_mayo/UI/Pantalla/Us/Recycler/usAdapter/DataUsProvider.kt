package com.example.proyecto_mayo.UI.Pantalla.Us.Recycler.usAdapter

import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataMembers
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataUs
import com.example.proyecto_mayo.R

class DataUsProvider {
    companion object{

        var usList = listOf<DataUs>(

            DataUs(R.drawable.perro,"Jhon"),
            DataUs(R.drawable.detailfondo,"Mariano"),
            DataUs(R.drawable.selfie,"Eliseo"),




            )
    }
}