package com.example.marsroverapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsroverapi.api.Repository
import com.example.marsroverapi.model.DatosAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _marsPhotos = MutableLiveData<DatosAPI>()
    val marsPhotos = _marsPhotos

    fun fetchMarsPhotos(sol: Int, apiKey: String){
        CoroutineScope(Dispatchers.IO).launch{
            val response = repository.getMarsPhotos(sol, apiKey)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _marsPhotos.value = response.body()
                    _loading.value = false
                }else {
                    Log.e("Error API: ", response.message())
                }
            }
        }
    }
}