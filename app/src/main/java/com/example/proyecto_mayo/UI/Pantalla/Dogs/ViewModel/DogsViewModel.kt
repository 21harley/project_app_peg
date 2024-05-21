package com.example.proyecto_mayo.UI.Pantalla.Dogs.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogsViewModel: ViewModel() {
    private val _data = MutableLiveData<StateDogs>()
    val data: LiveData<StateDogs> get() = _data


    fun callDogApi(){
        _data.postValue(StateDogs.Loading)
        viewModelScope.launch(Dispatchers.IO){
            try {
                val service = DogApiClient.service
                val response = service.getRandomImage(12)
                val call = response.execute().body()
                //Log.i("HOLA",call.toString())
                _data.postValue(StateDogs.Success(call))
            } catch (e: Exception) {
                //Log.i("HOLA",e.message.toString())
                _data.postValue(StateDogs.Error("Error service"))
            }
        }
    }
}
