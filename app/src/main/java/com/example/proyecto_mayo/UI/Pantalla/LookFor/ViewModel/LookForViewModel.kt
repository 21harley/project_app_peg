package com.example.proyecto_mayo.UI.Pantalla.LookFor.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_mayo.Data.Services.DogApi.Class.DogApiClient
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateBreedDog
import com.example.proyecto_mayo.Data.Services.DogApi.DTO.StateDog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LookForViewModel : ViewModel() {
    private val _data = MutableLiveData<StateBreedDog>()
    val data: LiveData<StateBreedDog?> get() = _data

    fun callDogApi(razaPerro:String){
        _data.postValue(StateBreedDog.Loading)
        viewModelScope.launch(Dispatchers.IO){
            try {
                val service = DogApiClient.service
                val response = service.getRandomImageBreedRandom(razaPerro)
                val call = response.execute().body()
                if(call!=null){
                    Log.i("HOLA",call.toString())
                    _data.postValue(StateBreedDog.Success(call))
                }else{
                    _data.postValue(StateBreedDog.Error("Error service"))
                }
            } catch (e: Exception) {
                Log.i("HOLA",e.message.toString())
                _data.postValue(StateBreedDog.Error("Error service"))
            }
        }
    }
}