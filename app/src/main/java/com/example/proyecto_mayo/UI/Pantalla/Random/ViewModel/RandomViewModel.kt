package com.example.proyecto_mayo.UI.Pantalla.Random.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomViewModel : ViewModel() {
    private val _data = MutableLiveData<StateDog>()
    val data: LiveData<StateDog> get() = _data

    fun callDogApi(){
        _data.postValue(StateDog.Loading)
        viewModelScope.launch(Dispatchers.IO){
            try {
                val service = DogApiClient.service
                val response = service.getRandomImage()
                val call = response.execute().body()
                //Log.i("HOLA",call.toString())
                _data.postValue(StateDog.Success(call))
            } catch (e: Exception) {
                //Log.i("HOLA",e.message.toString())
                _data.postValue(StateDog.Error("Error service"))
            }
        }
    }
}