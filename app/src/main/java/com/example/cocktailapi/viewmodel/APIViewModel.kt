package com.example.cocktailapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailapi.api.Repository
import com.example.cocktailapi.model.DataAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _cocktailData = MutableLiveData<DataAPI?>()
    val cocktailData: LiveData<DataAPI?> = _cocktailData

    fun searchCocktail(name: String) {

        if (name.isBlank()) {
            Log.e("Error API", "No se puede buscar un cocktail con un nombre vacío")
            return
        }

        _cocktailData.value = null
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.searchCocktailByName(name)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cocktailData.value = response.body()
                } else {
                    Log.e("Error API", response.message())
                }
                _loading.value = false
            }
        }
    }

    fun fetchRandomCocktail() {
        _cocktailData.value = null
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getRandomCocktail()
            Log.e("API", response.message())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cocktailData.value = response.body()
                } else {
                    Log.e("Error API", response.message())
                }
                _loading.value = false
            }
        }
    }

    fun getCocktailById(id: String) {
        _cocktailData.value = null
        _loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCocktailById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _cocktailData.value = response.body()
                } else {
                    Log.e("Error API", response.message())
                }
                _loading.value = false
            }
        }
    }

    fun clearCocktailData() {
        _cocktailData.value = null
    }
}