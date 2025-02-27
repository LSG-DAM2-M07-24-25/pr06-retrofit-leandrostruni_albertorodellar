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

class CocktailViewModel : ViewModel() {
    private val repository = Repository()
    private val _cocktailData = MutableLiveData<DataAPI?>()
    val cocktailData: LiveData<DataAPI?> = _cocktailData
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchFilteredCocktails(isOrdinary: Boolean, isCocktail: Boolean) {
        val categories = mutableListOf<String>()
        if (isOrdinary) categories.add("Ordinary_Drink")
        if (isCocktail) categories.add("Cocktail")

        if (categories.isEmpty()) {
            _cocktailData.postValue(DataAPI(emptyList()))
            return
        }

        _loading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val drinksList = mutableListOf<Drink>()

            for (category in categories) {
                val response = repository.getCocktailByCategory(category)
                Log.d("API Response", "Categoría: $category - Código: ${response.code()} - Cuerpo: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.drinks?.let { drinksList.addAll(it) }
                } else {
                    Log.e(
                        "API Error",
                        "Error al obtener cócteles de $category: ${response.message()}"
                    )
                }
            }

            withContext(Dispatchers.Main) {
                _cocktailData.value = DataAPI(drinksList)
                _loading.value = false
            }
        }
    }
}