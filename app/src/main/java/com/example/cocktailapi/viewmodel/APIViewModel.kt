package com.example.cocktailapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailapi.api.Repository
import com.example.cocktailapi.model.DataAPI
import com.example.cocktailapi.model.Drink
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
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getRandomCocktail()
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

    fun fetchCocktailByCategory(categories: List<String>) {
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val drinksList = mutableListOf<Drink>()
            for (category in categories) {
                val response = repository.getCocktailByCategory(category)
                if (response.isSuccessful) {
                    response.body()?.drinks?.let { drinksList.addAll(it) }
                } else {
                    Log.e(
                        "Error API",
                        "Error al obtener cócteles de $category: ${response.message()}"
                    )
                }

                withContext(Dispatchers.Main) {
                    _cocktailData.value = DataAPI(drinksList)
                    _loading.value = false
                }
            }
        }
    }

    fun clearCocktails() {
        TODO("Not yet implemented")
    }
}