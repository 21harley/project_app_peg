package com.example.proyecto_mayo.UI.Pantalla.Home.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient.service
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DogBreedResponse
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DogBreedsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.lifecycle.ViewModelStore
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.DataAdopt
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.RandomImagesResponse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException


class HomeViewModel : ViewModel() {
    private val _data = MutableLiveData<RandomImagesResponse?>()
    val data: LiveData<RandomImagesResponse?>
        get() = _data

    init {
        callDogApi()
    }

    private fun callDogApi() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val service = DogApiClient.service
                val allBreedsCall = service.getRandomImage(3)
                val allBreedsResponse = allBreedsCall.execute().body()
                _data.postValue(allBreedsResponse)
            } catch (e: IOException) {
                // Manejar excepciones de red
                e.printStackTrace()
            } catch (e: HttpException) {
                // Manejar errores HTTP
                e.printStackTrace()
            }
        }
    }

    fun returnListData(): List<DataAdopt> {
        val auxData: MutableList<DataAdopt> = mutableListOf()
        data.value?.message?.forEach {
            auxData.add(DataAdopt(it))
        }
        return auxData
    }
}


